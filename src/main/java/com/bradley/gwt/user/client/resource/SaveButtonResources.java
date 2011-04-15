package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;

public interface SaveButtonResources extends ButtonResources {
	public interface SaveStyle extends Style {}
	
	@Source("save-button.css")
	SaveStyle style();
	
	@Source("image/disk.png")
	ImageResource save();
}
