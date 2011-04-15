package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.bradley.gwt.user.client.resource.CancelButtonResources;
import com.google.gwt.core.client.GWT;

public class CancelButton extends Button {
	
	
	public CancelButton() {
		super(UIConstants.INSTANCE.cancel(), 
				(CancelButtonResources) GWT.create(CancelButtonResources.class));
	}
}
