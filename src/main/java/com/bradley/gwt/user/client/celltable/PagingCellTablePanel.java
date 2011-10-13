package com.bradley.gwt.user.client.celltable;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.bradley.gwt.user.client.resource.SimplePagerClientBundle;
import com.bradley.gwt.user.client.resource.UIClientBundle;
import com.bradley.gwt.user.client.ui.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.Resources;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.gwt.user.cellview.client.AbstractCellTable;

/**
 * A cell table that is encompassed by a panel. This bundles a tool bar
 * and a paginator into one neat package.
 *
 * @param <T> The type in the CellTable.
 */
public class PagingCellTablePanel<T extends EntityProxy> extends Composite {

	interface Binder extends UiBinder<Widget, PagingCellTablePanel<?>> {
	};

	@UiField
	protected ToolBar toolbar;
	
	@UiField
	protected SimplePanel headerContainer;

	@UiField
	protected SimplePanel body;
	
	@UiField
	protected SimplePanel footer;
	
	protected Widget header;
	
	protected AbstractCellTable<T> table;
	
	protected Label noDataAvailable;
	
	protected AbstractPager pager;
	
	protected static final int PAGE_SIZE = 25;
		
	public PagingCellTablePanel(AbstractCellTable<T> table) {
		this(table, new SimplePager(TextLocation.CENTER, (Resources) GWT.create(SimplePagerClientBundle.class), false, 0, true));
		SimplePager p = (SimplePager) pager;
		p.setPageSize(PAGE_SIZE);
	}
	
	public PagingCellTablePanel(AbstractCellTable<T> table, AbstractPager pager) {
		UIClientBundle.INSTANCE.getUICssResource().ensureInjected();
		
		this.table = table;
		this.pager = pager;
		
		Binder binder = GWT.create(Binder.class);
		initWidget(binder.createAndBindUi(this));
		body.setWidget(table);
	
		footer.setWidget(pager);
		
		pager.setDisplay(table);
		
		noDataAvailable = new Label(UIConstants.INSTANCE.noDataAvailable());
		noDataAvailable.addStyleName(UIClientBundle.INSTANCE.getUICssResource().noData());
		table.setEmptyTableWidget(noDataAvailable);
	}
	
	public AbstractCellTable<T> getTable() {
		return table;
	}
	
	/**
	 * Get the pager that controls pagination of this cell table.
	 * 
	 * @return The pager.
	 */
	public AbstractPager getPager() {
		return pager;
	}
	
	public void setHeader(Widget header) {
		headerContainer.setWidget(header);
		this.header = header;
	}
	
	public Widget getHeader() {
		return header;
	}

	/**
	 * Get the top tool bar of this panel.
	 * 
	 * @return The top tool bar.
	 */
	public ToolBar getToolBar() {
		return toolbar;
	}
	
	@UiHandler("container")
	void onResize(ResizeEvent event) {
		int height = event.getHeight();
		height -= 94; // TODO: Get rid of this
		
		if (height > 0) {
			body.setHeight(height + "px");
		}
	}
}
