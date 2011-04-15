package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface ButtonResources extends ClientBundle {

	public interface Style extends CssResource {
		
		@ClassName("button")
		String button();
	}

	@Source("../resource/button.css")
	Style style();
}
