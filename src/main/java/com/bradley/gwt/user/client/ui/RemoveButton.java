package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.RemoveButtonResources;
import com.google.gwt.core.client.GWT;

public class RemoveButton extends Button {

	public RemoveButton(String label) {
		super(label, (RemoveButtonResources) GWT.create(RemoveButtonResources.class));
	}
	
	public RemoveButton() {
		this("");
	}
}
