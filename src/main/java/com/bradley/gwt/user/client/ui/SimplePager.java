package com.bradley.gwt.user.client.ui;

public class SimplePager extends com.google.gwt.user.cellview.client.SimplePager {

	public SimplePager() {
		this(TextLocation.CENTER);
	}
	
	public SimplePager(TextLocation location) {
		this(location, null, false, 1, false);
	}
	
	public SimplePager(TextLocation location, Resources resources, boolean showFastForwardButton, int fastForwardRows, boolean showLastPageButton) {
		super(location, resources, showFastForwardButton, fastForwardRows, showLastPageButton);
	}
}
