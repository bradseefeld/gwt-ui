package com.bradley.gwt.user.client.celltable;

import com.bradley.gwt.user.client.resource.UIClientBundle;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;

public class BooleanCell extends AbstractCell<Boolean> {

	@Override
	public void render(Cell.Context context, Boolean value, SafeHtmlBuilder sb) {
		if (!value) {
			return;
		}
		
		Image img = new Image(UIClientBundle.INSTANCE.tick());
		img.addStyleName("is-true");
		
		sb.appendHtmlConstant(img.toString());
	}
}
