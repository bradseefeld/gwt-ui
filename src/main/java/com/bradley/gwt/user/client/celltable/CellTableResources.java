package com.bradley.gwt.user.client.celltable;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;

public interface CellTableResources extends CellTable.Resources {
	
	public interface CellTableStyle extends CellTable.Style {
	};

	@Override
	@Source({ "cell-table.css" })
	CellTableStyle cellTableStyle();
	
	@Override
	@Source("large-loading.gif")
	ImageResource cellTableLoading();
}
