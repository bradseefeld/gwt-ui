package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface DialogClientBundle extends ClientBundle {

	public interface Style extends CssResource {}
	
	@Source("dialog.css")
	Style getStyle();
	
	@Source("image/dialog-bg.png")
	ImageResource titleBackground();
}
