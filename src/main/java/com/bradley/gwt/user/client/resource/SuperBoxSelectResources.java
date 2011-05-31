package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface SuperBoxSelectResources extends ClientBundle {
	
	@Source("superbox-select.css")
	SuperBoxSelectCssResource getSuperBoxSelectCss();
	
	@Source("image/close.png")
	ImageResource close();
}
