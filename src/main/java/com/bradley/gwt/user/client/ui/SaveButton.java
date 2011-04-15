package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.bradley.gwt.user.client.resource.SaveButtonResources;
import com.google.gwt.core.client.GWT;

public class SaveButton extends Button {
	
	public SaveButton() {
		super(UIConstants.INSTANCE.save(),
				(SaveButtonResources) GWT.create(SaveButtonResources.class));
	}
}
