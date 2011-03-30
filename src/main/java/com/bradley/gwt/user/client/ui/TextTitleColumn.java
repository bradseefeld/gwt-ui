package com.bradley.gwt.user.client.ui;

import com.google.gwt.user.cellview.client.Column;

/**
 * A TextColumn that also puts its value in the title attribute of its containing
 * div. This allows columns that may be have absolute widths to display their
 * entire contents in the title tooltip when a user is hovered over the cell.
 * 
 * @author bseefeld
 *
 * @param <T>
 */
public abstract class TextTitleColumn<T> extends Column<T, String>{

	public TextTitleColumn() {
		super(new TextTitleCell());
	}
}
