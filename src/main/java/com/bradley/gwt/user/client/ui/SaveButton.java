package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.github.gwtbootstrap.client.ui.constants.IconType;

public class SaveButton extends com.github.gwtbootstrap.client.ui.Button {
	
	public SaveButton() {
		this(UIConstants.INSTANCE.save());
	}
	
	public SaveButton(String label) {
		super(label, IconType.SAVE);
	}
}
