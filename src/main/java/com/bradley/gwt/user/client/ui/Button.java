package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.ButtonResources;
import com.bradley.gwt.user.client.resource.ButtonResources.Style;
import com.bradley.gwt.user.client.resource.UIClientBundle;
import com.bradley.gwt.user.client.resource.UICssResource;
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
	
	private static final UICssResource _css = UIClientBundle.INSTANCE.getUICssResource();
	
	public Button(String label, ButtonResources resources) {
		setHTML(label);
		
		this.resources = resources;
		this.css = resources.style();
		
		addStyleName(css.button());
		
		css.ensureInjected();
		_css.ensureInjected();
	}

	public Button(String label) {
		this(label, (ButtonResources) GWT.create(ButtonResources.class));
	}
	
	public Button() {
		this("");
	}
}
