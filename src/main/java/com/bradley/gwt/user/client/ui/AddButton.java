package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.AddButtonResources;
import com.google.gwt.core.client.GWT;

public class AddButton extends Button {

	public AddButton(String label) {
		super(label, (AddButtonResources) GWT.create(AddButtonResources.class));
	}
	
	public AddButton() {
		this("");
	}
}
