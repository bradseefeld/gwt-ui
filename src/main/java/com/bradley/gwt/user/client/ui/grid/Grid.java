package com.bradley.gwt.user.client.ui.grid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * An excel like grid. Has many rows/columns.
 */
public class Grid extends Composite {

	interface Binder extends UiBinder<Widget, Grid> {
	}
	
	/** The number of rows in the grid. */
	protected int numRows;
	
	/** The number of columns in the grid. TODO: Not sure why this is needed now? */
	protected int numCols;
	
	protected int rowHeight = 25;
	
	protected float scale = 1.00F;
	
	@UiField
	protected ScrollPanel headerScrollPanel;
	
	@UiField
	protected FlowPanel headerContainer;
	
	@UiField
	protected ScrollPanel gridScrollPanel;
	
	@UiField
	protected ScrollPanel rowLabelScrollPanel;
	
	@UiField
	protected FlowPanel rowLabelContainer;
	
	@UiField
	protected AbsolutePanel container;
	
	@UiField
	protected FlowPanel horizontalGrid;
	
	@UiField
	protected FlowPanel verticalGrid;
	
	@UiField
	protected AbsolutePanel contentGrid;
	
	public Grid(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		
		Binder binder = GWT.create(Binder.class);
		initWidget(binder.createAndBindUi(this));
		
		// TODO: This should be part of UI Binder.
		contentGrid.getElement().getStyle().setOverflow(Overflow.VISIBLE);
		container.getElement().getStyle().setOverflow(Overflow.VISIBLE);
		
		// Specifically set absolute style or IE seems to ignore it in the class.
		// TODO: This should be part of UI Binder.
		verticalGrid.getElement().getStyle().setPosition(Position.ABSOLUTE);
		
		gridScrollPanel.addScrollHandler(new ScrollHandler() {

			@Override
			public void onScroll(ScrollEvent event) {
				
				int x = event.getRelativeElement().getScrollLeft();
				int y = event.getRelativeElement().getScrollTop();
				
				headerScrollPanel.setHorizontalScrollPosition(x);
				rowLabelScrollPanel.setScrollPosition(y);
			}
		});
	}
	
	public int getRowHeight() {
		
		int height = Math.round(rowHeight * scale) + 1;//borderWidth;
		
		/*if (GXT.isIE) {
			return height + 1;
		}*/
		
		return height;
	}
}
