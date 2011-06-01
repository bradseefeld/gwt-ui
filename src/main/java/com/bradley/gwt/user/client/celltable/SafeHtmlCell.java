package com.bradley.gwt.user.client.celltable;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class SafeHtmlCell extends AbstractCell<String> {

	@Override
	public void render(Context context, String value, SafeHtmlBuilder sb) {
		if (value == null) {
			return;
		}
		
		sb.appendHtmlConstant(value);
	}
}
