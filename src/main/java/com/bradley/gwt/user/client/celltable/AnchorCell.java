package com.bradley.gwt.user.client.celltable;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Anchor;

public class AnchorCell extends AbstractCell<Anchor> {

	@Override
	public void render(Context context, Anchor value, SafeHtmlBuilder sb) {
		if (value == null) {
			return;
		}
		
		sb.appendHtmlConstant(value.toString());
	}
}
