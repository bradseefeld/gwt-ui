package com.bradley.gwt.user.client.celltable;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * A TextColumn that also puts its value in the title attribute of its containing
 * div. This allows columns that may be have absolute widths to display their
 * entire contents in the title tooltip when a user is hovered over the cell.
 * 
 * @author bseefeld
 */
public class TextTitleCell extends AbstractCell<String> {

	@Override
	public void render(Cell.Context context, String value, SafeHtmlBuilder sb) {
		
		if (value == null) {
			return;
		}
		
		SafeHtml safeValue = SafeHtmlUtils.fromString(value);
		
	    sb.appendHtmlConstant("<div title=\"" + safeValue.asString() + "\">");
	    sb.append(safeValue);
	    sb.appendHtmlConstant("</div>");
	}
}
