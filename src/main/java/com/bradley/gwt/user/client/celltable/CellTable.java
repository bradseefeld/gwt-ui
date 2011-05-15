package com.bradley.gwt.user.client.celltable;

import com.google.gwt.core.client.GWT;

/**
 * A CellTable that uses a different set of styles by default.
 * 
 * @param <T> The type being represented by the table.
 */
public class CellTable<T> extends com.google.gwt.user.cellview.client.CellTable<T> {

	/**
	 * Default constructor.
	 */
	public CellTable() {
		super(PagingCellTablePanel.PAGE_SIZE, (Resources) GWT.create(CellTableResources.class));
	}
}
