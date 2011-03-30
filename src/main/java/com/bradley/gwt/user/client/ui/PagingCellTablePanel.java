package com.bradley.gwt.user.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

/**
 * A cell table that is encompassed by a panel. This bundles a tool bar
 * and a paginator into one neat package.
 * 
 * TODO When filters are introduced, create a filter interface that defines a name
 * and value method. The paginate method can then just iterate through all entities
 * in the toolbar with this interface.
 * 
 * @author bseefeld
 *
 * @param <T> The type in the CellTable.
 */
public abstract class PagingCellTablePanel<T extends EntityProxy> extends Composite {

	interface Binder extends UiBinder<Widget, PagingCellTablePanel<?>> {
	};

	@UiField
	protected ToolBar toolbar;

	@UiField
	protected SimplePanel body;

	@UiField
	protected SimplePanel footer;
	
	protected CellTable<T> table;
	
	protected AbstractPager pager;
	
	protected AsyncDataProvider<T> dataProvider;

	protected static final int PAGE_SIZE = 25;
	
	private static final Logger logger = Logger.getLogger(PagingCellTablePanel.class.getName());

	public PagingCellTablePanel(CellTable<T> table) {
		this(table, new SimplePager());
		
		SimplePager p = (SimplePager) pager;
		p.setPageSize(PAGE_SIZE);
	}
	
	public PagingCellTablePanel(CellTable<T> table, AbstractPager pager) {

		this.table = table;
		this.pager = pager;

		Binder binder = GWT.create(Binder.class);
		initWidget(binder.createAndBindUi(this));
		body.setWidget(table);
		
		body.setSize("600px", "400px");
		footer.setWidget(pager);
		
		toolbar.setVisible(false);
		
		initializePagination(pager);
		initializeSorting();
		
		// TODO It might be helpful to periodically update the count in the paginator.
	}
	
	/**
	 * Get the pager that controls pagination of this cell table.
	 * 
	 * @return The pager.
	 */
	public AbstractPager getPager() {
		return pager;
	}

	/**
	 * Get the top tool bar of this panel.
	 * 
	 * @return The top tool bar.
	 */
	public ToolBar getToolBar() {
		return toolbar;
	}
	
	public abstract void paginate(final int offset, int limit, String sortColumn, boolean isSortAscending);
	
	public abstract void count(Map<String, String> filters);
	
	public void count() {
		count(new HashMap<String, String>());
	}
	
	protected void paginate(Request<List<T>> request, final int offset) {
		request.fire(new Receiver<List<T>>() {

			@Override
			public void onSuccess(List<T> data) {
				dataProvider.updateRowData(offset, data);
			}
		});
	}
	
	/**
	 * Add pager to display and setup listeners. Dispatch the call to load the
	 * item count.
	 * 
	 * @param pager The page entity that will paginate through results.
	 */
	protected void initializePagination(AbstractPager pager) {
		pager.setDisplay(table);

		dataProvider = new AsyncDataProvider<T>() {

			@Override
			protected void onRangeChanged(HasData<T> display) {
				Range r = display.getVisibleRange();
				paginate(r.getStart(), r.getLength(), "todo", true);
			}
		};
		
		dataProvider.addDataDisplay(table);
		
		// We initially assume 1k items.
		dataProvider.updateRowCount(1000, false);
	    
	    // Load the total count in a delayed task.
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				//count();
			}
		});
	}
	
	/**
	 * Execute the given request to determine the current count of entities
	 * provided by the server. If the grid is filtered, the given request should
	 * have already taken that into account.
	 * 
	 * @param request The request to execute to get the count.
	 */
	protected void count(Request<Integer> request) {
	    request.fire(new Receiver<Integer>() {
	    	
	    	@Override
	    	public void onSuccess(Integer count) {
	    		dataProvider.updateRowCount(count, true);
	    	}
	    });
	}
	
	protected void initializeSorting() {
		AsyncHandler columnSortHandler = new AsyncHandler(table) {
			
			@Override
			public void onColumnSort(ColumnSortEvent event) {
				Range r = table.getVisibleRange();
				paginate(r.getStart(), r.getLength(), "todo", event.isSortAscending());
			}
		};
	    table.addColumnSortHandler(columnSortHandler);
	}
}
