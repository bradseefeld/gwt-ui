package com.bradley.gwt.user.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface UIClientBundle extends ClientBundle {

	public static final UIClientBundle INSTANCE = GWT.create(UIClientBundle.class);
	
	@Source("styles.css")
	UICssResource getUICssResource();
	
	@Source("image/tick.png")
	ImageResource tick();
	
	@Source("image/error.png")
	ImageResource warn();
	
	@Source("image/exclamation.png")
	ImageResource error();
}
