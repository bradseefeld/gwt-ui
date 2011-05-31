package com.bradley.gwt.user.client.ui.grid.schedule;

import java.util.Date;

import com.bradley.gwt.user.client.ui.grid.Grid;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

public class SchedulableGrid extends Grid {

	/** The hour that the schedule starts at. Typically 0, for midnight. */
	protected int startHour = 0;
	
	/** The minute that the schedule starts at. Typically 0, for the top of the hour. */
	protected int startMinute = 0;
	
	/** The day this schedule represents. */
	protected Date day;
	
	protected int rowsPerHour;
	
	protected Panel currentTimeBar;
	
	public SchedulableGrid(Date day, int rowsPerHour) {
		super(24 * rowsPerHour, 1);
		
		this.rowsPerHour = rowsPerHour;
		this.day = day;
		
		initTimeBar();
	}

	/**
	 * Convert a date to a Y pixel location on the scheduler. The given UTC date
	 * is mapped to a Y coordinate using the start hour/minute and the row height
	 * defined by this grid. 
	 * 
	 * @param utc
	 * @return
	 */
	public int dateToPixel(Date utc) {
		
		int hours = DateMath.extractHour(utc) - startHour;
		if (hours < 0) {
			//hours += hoursDisplayed; // Why are we doing this?
		}
		
		int minutes = DateMath.extractMinute(utc) - startMinute;
		if (minutes < 0) {
			hours--; // Borrow from the hours
			minutes += 60;
		}
		
		float totalHours = Math.max(minutes / 60f + hours, 0f);
		
		return Math.round(totalHours * getRowHeight() * rowsPerHour);
	}
	
	/**
	 * Create a horizontal bar that spans the schedule showing the current time.
	 */
	protected void initTimeBar() {
		currentTimeBar = new SimplePanel();
		currentTimeBar.addStyleName("current-time-bar");
		container.add(currentTimeBar);
		int y = dateToPixel(new Date());
		currentTimeBar.getElement().getStyle().setTop(y, Unit.PX);
		
		Timer t = new Timer() {

			@Override
			public void run() {
				int y = dateToPixel(new Date());
				currentTimeBar.getElement().getStyle().setTop(y, Unit.PX);
			}
		};
		t.scheduleRepeating(1 * 60 * 1000); // Repeat every 1 minutes
	}
}
