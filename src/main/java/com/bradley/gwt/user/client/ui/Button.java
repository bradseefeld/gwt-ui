package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.ButtonResources;
import com.bradley.gwt.user.client.resource.ButtonResources.Style;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.PushButton;

/**
 * Different buttons now just implement different ButtonResource's and provide
 * different labels.
 * 
 */
public class Button extends PushButton {
	
	protected ButtonResources resources;
	
	protected Style css;
	
	public Button(String label, ButtonResources resources) {
		setHTML(label);
		
		this.resources = resources;
		this.css = resources.style();
		
		addStyleName(css.button());
		
		css.ensureInjected();
		
		// TODO: Add mouse out/in/down/up handler
	}

	public Button(String label) {
		this(label, (ButtonResources) GWT.create(ButtonResources.class));
	}
}
