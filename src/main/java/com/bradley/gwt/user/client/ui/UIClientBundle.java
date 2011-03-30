package com.bradley.gwt.user.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource.NotStrict;

public interface UIClientBundle extends ClientBundle {

	public static final UIClientBundle INSTANCE = GWT.create(UIClientBundle.class);
	
	@Source("styles.css")
	UICssResource getUICssResource();
}
