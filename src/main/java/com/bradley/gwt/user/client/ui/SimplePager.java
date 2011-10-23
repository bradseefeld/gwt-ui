package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.SimplePagerClientBundle;
import com.google.gwt.core.client.GWT;

public class SimplePager extends com.google.gwt.user.cellview.client.SimplePager {

	public SimplePager() {
		this(TextLocation.CENTER);
	}
	
	public SimplePager(TextLocation location) {
		this(location, (Resources) GWT.create(SimplePagerClientBundle.class), false, 1, false);
	}
	
	public SimplePager(TextLocation location, Resources resources, boolean showFastForwardButton, int fastForwardRows, boolean showLastPageButton) {
		super(location, resources, showFastForwardButton, fastForwardRows, showLastPageButton);
	}
}
