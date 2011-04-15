package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;

public interface CancelButtonResources extends ButtonResources {

	public interface CancelStyle extends Style {}
	
	@Source("cancel-button.css")
	CancelStyle style();
	
	@Source("image/cancel.png")
	ImageResource cancel();
}
