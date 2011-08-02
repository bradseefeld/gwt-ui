package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface ToolBarClientBundle extends ClientBundle {

	@Source("image/toolbar-bg.png")
	ImageResource background();
	
	@Source("toolbar.css")
	ToolBarCssResource style();
}
