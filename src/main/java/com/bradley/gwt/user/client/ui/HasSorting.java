package com.bradley.gwt.user.client.ui;

import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.event.shared.HandlerRegistration;

public interface HasSorting<T> extends HasData<T>, IsWidget {

	ColumnSortList getColumnSortList();
	
	Range getVisibleRange();
	
	HandlerRegistration addColumnSortHandler(ColumnSortEvent.Handler handler);
}
