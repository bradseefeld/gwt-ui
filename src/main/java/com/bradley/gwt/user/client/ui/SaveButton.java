package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.bradley.gwt.user.client.resource.SaveButtonResources;
import com.google.gwt.core.client.GWT;

public class SaveButton extends Button {
	
	public SaveButton() {
		this(UIConstants.INSTANCE.save());
	}
	
	public SaveButton(String label) {
		super(label,
				(SaveButtonResources) GWT.create(SaveButtonResources.class));
	}
}
