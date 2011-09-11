package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;

public interface SearchButtonClientBundle extends IconButtonResources {

	public interface SearchButtonStyle extends IconStyle {}

	@Source("icon-button.css")
	SearchButtonStyle style();
	
	@Override
	@Source("image/magnifier.png")
	ImageResource icon();
}
