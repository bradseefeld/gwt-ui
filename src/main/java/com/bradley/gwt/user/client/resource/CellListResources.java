package com.bradley.gwt.user.client.resource;

import com.google.gwt.user.cellview.client.CellList;

public interface CellListResources extends CellList.Resources {
	
	public interface Style extends CellList.Style {}
	
	@Source("css/cell-list.css")
	Style cellListStyle();
}
