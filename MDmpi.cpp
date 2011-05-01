// header files
#include "mpi.h"
#include <iostream>
#include <math.h>                // for pow( )
#include <stdlib.h>              // for rand( )
#include "Timer.h"

// constants
#define Nmax 1000                // maximum number of molecules
#define pixelsPerUnit 2          // space needed for each molecule
#define dt 0.02                  // time increment in natural units
#define wallStiffness 50.0       // "spring constant" for bouncing off wall
#define forceCutoff 3.0          // distance beyond which we don't bother to compute the force

using namespace std;

// global variables
int    N  = 0;                                   // currrent number of molecules
double t  = 0.0;                                 // clock time in natural units
double forceCutoff2 = forceCutoff * forceCutoff; // a squar of force cut off 
double pEatCutoff = 4 * ( pow( forceCutoff, -12.0 ) - pow( forceCutoff, -6.0 ) );

double boxWidth = 500 * 1.0 / pixelsPerUnit; // simulation space in a square 250 x 250
double gravity = 0;             // local gravitational constant in natural units

double x[Nmax];                 // x[i] = molecular i's location in x coordinate
double y[Nmax];                 // y[i] = molecular i's location in y coordinate
double vx[Nmax];                // vx[i] = molecular i's velocity in x coordinate
double vy[Nmax];                // xy[i] = molecular i's velocity in y coordinate
double ax[Nmax];                // ax[i] = molecular i's acceleration in x coordinate
double ay[Nmax];                // ax[i] = molecular i's acceleration in x coordinate

int my_rank;  // My rank in the MPI pool of nodes
int mpi_size; // The number of MPI nodes in the pool

/* 
 * Add a molecule at the first available space (if any)
 * @return true if a new molecule was added successfully, otherwise false.
 */
bool addMolecule( ) {
  double buffer = 1.3;          // minimum space required between molecule centers
  double epsilon = 0.01;        // small distance
  if ( N == Nmax )              // quit if max number of molecules is already reached
    return false;
  double xTest = buffer / 2;    // start looking for space at lower-left corner
  double yTest = buffer / 2;

  while (true) {  
    // this loop actually does terminate, when one or the other "return" statement is reached
    bool spaceOpen = true;          // temporarily assume this space is available
    for ( int i = 0; i < N; i++ ) { // check all other molecules to see if any are too close
      if ( ( fabs( x[i] - xTest ) < buffer - epsilon ) && 
	   ( fabs( y[i] - yTest ) < buffer-epsilon ) ) {
	spaceOpen = false;
	break;
      }
    }
    if ( spaceOpen ) {
      // random nudge is to avoid too much symmetry
      x[N] = xTest + ( double( rand( ) ) / double( RAND_MAX ) - 0.5 ) * epsilon; 
      y[N] = yTest + ( double( rand( ) ) / double( RAND_MAX ) - 0.5 ) * epsilon;
      vx[N] = 0; vy[N] = 0;
      ax[N] = 0; ay[N] = 0;
      N++;
      return true;
    } else {                // if this space isn't open, try the next...
      xTest += buffer;
      if ( xTest + buffer / 2 > boxWidth ) { // reached maximum X
	xTest = buffer / 2;
	yTest += buffer;
	if ( yTest + buffer/2 > boxWidth )   // reached maximum Y
	  return false;                      // no more space
      }
    }
  }
}

/*
 * Computes accelerations of all atoms from current positions
 */
void computeAccelerations( ) {
  
  int index = 0;
  if (mpi_size > 0) index = my_rank * N / mpi_size;
  int length = N;
  if (mpi_size > 0) length = N / mpi_size;

  // change each atom's acceleration when it bounce against a wall
  for (int i = 0; i < length; i++) {
    // checking if an atom is bouncing against the left or right boundary
    if ( x[index] < 0.5 ) {                       // bouncing against the left boudary
      ax[index] = wallStiffness * ( 0.5 - x[index] );
    } else if ( x[index] > ( boxWidth - 0.5 ) ) { // bouncing against the right boundary
      ax[index] = wallStiffness * ( boxWidth - 0.5 - x[index] );
    } else                                    // in a middle of the simulation box
      ax[index] = 0.0;

    // checking if an atom is bouncing against the upper or lower boundary
    if ( y[index] < 0.5 ) {                       // bouncing againt the lower boundary
      ay[index] = wallStiffness * ( 0.5 - y[index] );
    } else if ( y[index] > ( boxWidth - 0.5 ) ) { // bouncing againt the upper boundary
      ay[index] = wallStiffness * ( boxWidth - 0.5 - y[index] );
    } else                                    // in a middle of the simulation box
      ay[index] = 0;

    // add gravity
    ay[index] -= gravity;
    index++;
  }

  // Now compute interaction forces (Lennard-Jones potential).
  // This is where the program spends most of its time (when N is reasonably large),
  // so we carefully avoid unnecessary calculations and array lookups.

  double dx, dy;  // separations in x and y directions
  double dx2, dy2, rSquared, rSquaredInv, attract, repel, fOverR, fx, fy;
  
  index = 0;
  if (mpi_size > 0) index = my_rank * N / mpi_size;
  index += 1;
  length = N;
  if (mpi_size > 0) length = N / mpi_size;
  length += index - 1;
  for ( int i = index; i < length; i++ ) {           // for each molecule
    for (int j = 0; j < i; j++) {           // loop over all distinct pairs
      dx = x[i] - x[j];
      dx2 = dx * dx;
      if ( dx2 < forceCutoff2 ) {           // make sure they're close enough to bother
        dy = y[i] - y[j];
	dy2 = dy * dy;
	if ( dy2 < forceCutoff2 ) {         
	  rSquared = dx2 + dy2;
	  if ( rSquared < forceCutoff2 ) {  // yes, these two molecules are very close!
                                   	    // now compute their collision 
	    rSquaredInv = 1.0 / rSquared;
      	    attract = rSquaredInv * rSquaredInv * rSquaredInv;
      	    repel = attract * attract;
      	    fOverR = 24.0 * ( ( 2.0 * repel ) - attract ) * rSquaredInv;
      	    fx = fOverR * dx;
      	    fy = fOverR * dy;
      	    ax[i] += fx;  // add this force on to i's acceleration ( mass = 1 )
      	    ay[i] += fy;
      	    ax[j] -= fx;  // Newton's 3rd law
      	    ay[j] -= fy;
      	  }
      	}
      }
    }
  }
  

  if (mpi_size > 0) { 
    // Join ax and ay back.
    double ax2[Nmax];
    double ay2[Nmax];
    MPI_Allreduce(ax, ax2, N, MPI_DOUBLE, MPI_SUM, MPI_COMM_WORLD);
    MPI_Allreduce(ay, ay2, N, MPI_DOUBLE, MPI_SUM, MPI_COMM_WORLD);
    memcpy(ax, ax2, Nmax * sizeof(double));
    memcpy(ay, ay2, Nmax * sizeof(double));
  }
}   // end of method computeAccelerations

void initialize( int n ) {
  for ( int i = 0; i < n; i++) 
    if ( addMolecule( ) == false )
      break;
  cout << "All molecules added. Computing initial accelerations..." << endl;
  computeAccelerations( );
}

/*
 * Executes one time step using the Verlet algorithm (from Gould and Tobochnik);
 * The physics is all in this method and computeAccelerations( ).
 */
void singleStep( ) {
  double dtOver2 = 0.5 * dt;
  double dtSquaredOver2 = 0.5 * dt * dt;
 
  int index = my_rank * N / mpi_size;
  int length = N / mpi_size;
  
  cout << my_rank << " will work from " << index << " on " << length << endl;
  for (int i = 0; i < length; i++ ) { // Compute each molecule's new position and velocity
    x[index] = ( vx[index] * dt ) + ( ax[index] * dtSquaredOver2 );  // update position
    y[index] = ( vy[index] * dt ) + ( ay[index] * dtSquaredOver2 );
    vx[index] = ( ax[index] * dtOver2 );                         // update velocity halfway
    vy[index] = ( ay[index] * dtOver2 );
    index++;
  }

  // TODO: Join x, y, vx, vy
  double x2[Nmax];
  MPI_Allreduce(x, x2, N, MPI_DOUBLE, MPI_SUM, MPI_COMM_WORLD);
  memcpy(x, x2, Nmax * sizeof(double));
  
  double y2[Nmax];
  MPI_Allreduce(y, y2, N, MPI_DOUBLE, MPI_SUM, MPI_COMM_WORLD);
  memcpy(y, y2, Nmax * sizeof(double));
  
  double vx2[Nmax];
  MPI_Allreduce(vx, vx2, N, MPI_DOUBLE, MPI_SUM, MPI_COMM_WORLD);
  memcpy(vx, vx2, Nmax * sizeof(double));
  
  double vy2[Nmax];
  MPI_Allreduce(vy, vy2, N, MPI_DOUBLE, MPI_SUM, MPI_COMM_WORLD);
  memcpy(vy, vy2, Nmax * sizeof( double ));
  
  computeAccelerations( );        // Compute each molecule's new acceleration
 
  index = my_rank * N / mpi_size;
  length = N / mpi_size; 
  for ( int i = 0; i < length; i++ ) {
    vx[index] = ( ax[index] * dtOver2 ); // finish updating velocity with new acceleration
    vy[index] = ( ay[index] * dtOver2 );
    index++;
  }
  t += dt;
}  // end of method singleStep

/*
 * Prints each molecule's position.
 */
void printMolecules( ) {
  if (my_rank != 0) {
    return;
  }
  
  cout << t << endl;
  for ( int i = 0; i < N; i++ )
    cout << x[i] << "\t" << y[i] << endl;
  cout << endl;
}

/*
 * Simultes moledular dynamics with "Nrequested" atoms for "maxSteps" and shows their
 * motions every "frequency". 
 */
int main( int argc, char *argv[] ) {

  int Nrequested = 1000;          // number of molecules requested by a user
  int maxSteps   = 10000;         // simulation lasts from 0 to maxTime - 1
  int frequency  = 100;           // frequency to show results

  // verify arguments
  if ( argc == 4 ) {
    Nrequested = atoi( argv[1] );
    maxSteps = atoi( argv[2] );
    frequency = atoi( argv[3] );
  } else if ( argc != 1 ) {
    cerr << "usage: MD #molecules maxSteps frequency" << endl;
    return -1;
  }
  cerr << "#molecules = " << Nrequested 
       << ", maxSteps = " << maxSteps 
       << ", frequency = " << frequency << endl;

  initialize( Nrequested );
  cout << "Program is initialized." << endl;

  MPI_Init( &argc, &argv ); // start MPI
  MPI_Comm_rank( MPI_COMM_WORLD, &my_rank );
  MPI_Comm_size( MPI_COMM_WORLD, &mpi_size );
  Timer timer;
  if (my_rank == 0) {
    // start a timer
    timer.start( );
  }
  
  MPI_Bcast(&x, 1, MPI_DOUBLE, 0, MPI_COMM_WORLD);
  MPI_Bcast(&y, 1, MPI_DOUBLE, 0, MPI_COMM_WORLD);
  MPI_Bcast(&vx, 1, MPI_DOUBLE, 0, MPI_COMM_WORLD);
  MPI_Bcast(&vy, 1, MPI_DOUBLE, 0, MPI_COMM_WORLD);
  MPI_Bcast(&ax, 1, MPI_DOUBLE, 0, MPI_COMM_WORLD);
  MPI_Bcast(&ay, 1, MPI_DOUBLE, 0, MPI_COMM_WORLD);
  
  // the main body of computation
  for ( int stepCount = 0; stepCount < maxSteps; stepCount++ ) {
    if ( frequency > 0 && stepCount % frequency == 0 )
      printMolecules();
    gravity = ( stepCount > maxSteps * 0.3 && stepCount < maxSteps * 0.4 ) ? 0.1 : 0.0;
    singleStep();
    cout << "End of iteration " << stepCount << " for " << my_rank << endl;
  }
  
  MPI_Finalize();
  cerr << "i am done" << endl;
  if (my_rank == 0) {
    // finished the timer
    cerr << "elapsed time = " << timer.lap( ) << endl;
  }
  return 0;
}

