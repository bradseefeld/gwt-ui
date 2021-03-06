package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;

public interface SaveButtonResources extends IconButtonResources {
	
	@Source("icon-button.css")
	IconStyle style();
	
	@Source("image/disk.png")
	ImageResource icon();
}
