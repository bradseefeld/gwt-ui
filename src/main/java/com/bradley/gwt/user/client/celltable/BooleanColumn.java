package com.bradley.gwt.user.client.celltable;

import com.google.gwt.user.cellview.client.Column;

public abstract class BooleanColumn<T> extends Column<T, Boolean>{

	public BooleanColumn() {
		super(new BooleanCell());
	}
}
