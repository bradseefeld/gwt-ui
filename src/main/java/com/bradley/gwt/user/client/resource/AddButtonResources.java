package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;

public interface AddButtonResources extends IconButtonResources {
	
	public interface AddStyle extends IconStyle {}
	
	@Source("icon-button.css")
	AddStyle style();
	
	@Source("image/add.png")
	ImageResource icon();
}
