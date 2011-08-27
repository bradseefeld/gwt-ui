package com.bradley.gwt.user.client.celltable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.bradley.gwt.user.client.resource.UIClientBundle;
import com.bradley.gwt.user.client.ui.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.AsyncHandler;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyChange;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.WriteOperation;

/**
 * A cell table that is encompassed by a panel. This bundles a tool bar
 * and a paginator into one neat package.
 * 
 * TODO When filters are introduced, create a filter interface that defines a name
 * and value method. The paginate method can then just iterate through all entities
 * in the toolbar with this interface.
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
	
	protected Label noDataAvailable;
	
	protected AbstractPager pager;
	
	protected AsyncDataProvider<T> dataProvider;
	
	protected Class<T> entityClass;

	protected static final int PAGE_SIZE = 25;
	
	private static final Logger logger = Logger.getLogger(PagingCellTablePanel.class.getName());
	
	public PagingCellTablePanel(Class<T> entityClass) {
		this(entityClass, new SimplePager());
		
		SimplePager p = (SimplePager) pager;
		p.setPageSize(PAGE_SIZE);
	}
	
	public PagingCellTablePanel(Class<T> entityClass, AbstractPager pager) {
		this.pager = pager;
		this.entityClass = entityClass;
		
	}
	
	public void initialize(final CellTable<T> table, RequestFactory requestFactory, EventBus eventBus) {
		this.table = table;
		
		requestFactory.initialize(eventBus);
		
		Binder binder = GWT.create(Binder.class);
		initWidget(binder.createAndBindUi(this));
		body.setWidget(table);
	
		footer.setWidget(pager);
		toolbar.setVisible(false);
		setTableHeight(450);
		
		initializePagination(pager);
		initializeSorting();
		
		EntityProxyChange.registerForProxyType(eventBus, entityClass, new EntityProxyChange.Handler<T>() {

			@Override
			public void onProxyChange(EntityProxyChange<T> event) {
				if (event.getWriteOperation() == WriteOperation.PERSIST ||
						event.getWriteOperation() == WriteOperation.DELETE) {
					logger.fine("Change (" + event.getWriteOperation().toString() + ") detected for " + entityClass.getName() + ". Reloading grid.");
					reload();
				}
			}
		});
		
		noDataAvailable = new Label(UIConstants.INSTANCE.noDataAvailable());
		noDataAvailable.addStyleName(UIClientBundle.INSTANCE.getUICssResource().noData());
	}
	
	public void setTableHeight(int pixels) {
		body.setHeight(pixels + "px");
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
	
	public void reload() {
		Range r = table.getVisibleRange();
		paginate(r.getStart(), r.getLength(), null, true);
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
				if (data.isEmpty()) {
					body.clear();
					body.add(noDataAvailable);
					footer.setVisible(false);
				} else {
					body.clear();
					body.add(table);
					if (!footer.isVisible()) {
						footer.setVisible(true);
					}
					dataProvider.updateRowData(offset, data);
				}
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
				paginate(r.getStart(), r.getLength(), null, true);
			}
		};
		
		dataProvider.addDataDisplay(table);
		
		// We initially assume 1k items.
		dataProvider.updateRowCount(1000, false);
	    
	    // Load the total count in a delayed task.
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				count();
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
				//Range r = table.getVisibleRange();
				//paginate(r.getStart(), r.getLength(), "todo", event.isSortAscending());
				logger.severe("I would like to sort, but it isnt wired up yet.");
			}
		};
	    table.addColumnSortHandler(columnSortHandler);
	}
}
