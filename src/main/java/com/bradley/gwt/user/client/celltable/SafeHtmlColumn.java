package com.bradley.gwt.user.client.celltable;

import com.google.gwt.user.cellview.client.Column;

public abstract class SafeHtmlColumn<T> extends Column<T, String>{

	public SafeHtmlColumn() {
		super(new SafeHtmlCell());
	}
}
