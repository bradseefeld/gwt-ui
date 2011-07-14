package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;

public interface RemoveButtonResources extends IconButtonResources {

	public interface RemoveStyle extends IconStyle {}
	
	@Source("icon-button.css")
	RemoveStyle style();
	
	@Source("image/delete.png")
	ImageResource icon();
}
