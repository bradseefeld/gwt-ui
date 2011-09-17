package com.bradley.gwt.user.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface NotifierClientBundle extends ClientBundle {
	
	public static final NotifierClientBundle INSTANCE = GWT.create(NotifierClientBundle.class);
	
	interface NotifierCssResource extends CssResource {
		String notifications();
		
		String success();
		
		String error();
		
		String warn();
		
		String info();		
	}
	
	@Source("notifier.css")
	NotifierCssResource style();
	
	@Source("image/error.png")
	ImageResource warn();
	
	@Source("image/exclamation.png")
	ImageResource error();
	
	@Source("image/tick.png")
	ImageResource tick();
}
