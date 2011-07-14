package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;

public interface IconButtonResources extends ButtonResources {

	public interface IconStyle extends Style {}

	@Source("icon-button.css")
	IconStyle style();

	ImageResource icon();
}
