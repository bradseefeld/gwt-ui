package com.bradley.gwt.user.client.celltable;

public abstract class SortableHyperlinkColumn<T> extends HyperlinkColumn<T> implements HasSortField {

	public SortableHyperlinkColumn() {
		super();
		setSortable(true);
	}
}
