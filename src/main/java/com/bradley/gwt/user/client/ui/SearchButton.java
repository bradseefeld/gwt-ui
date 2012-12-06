package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.github.gwtbootstrap.client.ui.constants.IconType;

public class SearchButton extends com.github.gwtbootstrap.client.ui.Button {
	
	public SearchButton() {
		this(UIConstants.INSTANCE.search());
	}
	
	public SearchButton(String label) {
		super(label, IconType.SEARCH);
	}
}
