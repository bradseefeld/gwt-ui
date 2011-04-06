package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.CssResource;

public interface UICssResource extends CssResource {
	
	@ClassName("button-mouse-over")
	String buttonMouseOver();
	
	@ClassName("button-icon")
	String buttonIcon();
	
	@ClassName("button-label")
	String buttonLabel();
		
	@ClassName("scrolled-dialog")
	String scrolledDialog();
}
