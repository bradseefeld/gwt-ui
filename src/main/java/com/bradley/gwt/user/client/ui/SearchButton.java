package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.bradley.gwt.user.client.resource.ButtonResources;
import com.bradley.gwt.user.client.resource.SearchButtonClientBundle;
import com.google.gwt.core.client.GWT;

public class SearchButton extends Button {
	
	public SearchButton() {
		this(UIConstants.INSTANCE.search());
	}
	
	public SearchButton(String label) {
		super(label, (ButtonResources) GWT.create(SearchButtonClientBundle.class));
	}
}
