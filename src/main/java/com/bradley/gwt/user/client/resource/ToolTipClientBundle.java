package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface ToolTipClientBundle extends ClientBundle {
	
	@Source("tooltip.css")
	ToolTipCssResource style();
	
	@Source("image/tooltip-bg.png")
	ImageResource background();
	
	@Source("javascript/tooltips.js")
	TextResource tooltips();
}
