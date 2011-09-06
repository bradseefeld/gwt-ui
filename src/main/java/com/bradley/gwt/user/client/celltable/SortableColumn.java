package com.bradley.gwt.user.client.celltable;

public abstract class SortableColumn<T> extends TextColumn<T> implements HasSortField {

	public SortableColumn() {
		super();
		setSortable(true);
	}
}
