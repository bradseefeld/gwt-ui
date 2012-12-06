package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.github.gwtbootstrap.client.ui.constants.IconType;

public class CancelButton extends com.github.gwtbootstrap.client.ui.Button {
	
	public CancelButton() {
		super(UIConstants.INSTANCE.cancel(), IconType.BAN_CIRCLE);
	}
}
