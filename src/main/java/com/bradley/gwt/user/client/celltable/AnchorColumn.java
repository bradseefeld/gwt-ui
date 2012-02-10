package com.bradley.gwt.user.client.celltable;

import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Anchor;

public abstract class AnchorColumn<T> extends Column<T, Anchor> {

	public AnchorColumn() {
		super(new AnchorCell());
	}	
}
