package com.bradley.gwt.user.client.celltable;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Hyperlink;

public abstract class HyperlinkColumn<T> extends Column<T, Hyperlink> {

	public HyperlinkColumn() {
		super(new HyperlinkCell());
	}
}
