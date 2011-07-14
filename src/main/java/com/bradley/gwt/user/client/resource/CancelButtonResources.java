package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;

public interface CancelButtonResources extends IconButtonResources {

	public interface CancelStyle extends IconStyle {}
	
	@Source("icon-button.css")
	CancelStyle style();
	
	@Source("image/cancel.png")
	ImageResource icon();
}
