package com.bradley.gwt.user.client.ui;

import com.google.gwt.user.cellview.client.CellTable;

public interface CellTableResources extends CellTable.Resources {
	public interface CellTableStyle extends CellTable.Style {
	};

	@Source({ "cell-table.css" })
	CellTableStyle cellTableStyle();
}
