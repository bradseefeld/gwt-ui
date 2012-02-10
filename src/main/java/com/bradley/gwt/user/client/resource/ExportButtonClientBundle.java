package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;

public interface ExportButtonClientBundle extends IconButtonResources {

public interface ExportStyle extends IconStyle {}
	
	@Source("icon-button.css")
	ExportStyle style();
	
	@Source("image/blue-document-excel.png")
	ImageResource icon();
}
