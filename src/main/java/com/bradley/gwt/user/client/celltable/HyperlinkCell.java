package com.bradley.gwt.user.client.celltable;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Hyperlink;

public class HyperlinkCell extends AbstractCell<Hyperlink> {

	@Override
	public void render(Context context, Hyperlink value, SafeHtmlBuilder sb) {
		sb.appendHtmlConstant(value.toString());
	}
}
