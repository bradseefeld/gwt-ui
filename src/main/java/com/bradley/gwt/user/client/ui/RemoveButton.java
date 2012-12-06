package com.bradley.gwt.user.client.ui;

import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;

public class RemoveButton extends com.github.gwtbootstrap.client.ui.Button {

	public RemoveButton(String label) {
		super(label, IconType.MINUS_SIGN);
		setType(ButtonType.DANGER);
	}
	
	public RemoveButton() {
		this("");
	}
}
