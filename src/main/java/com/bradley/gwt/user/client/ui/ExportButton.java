package com.bradley.gwt.user.client.ui;

import com.github.gwtbootstrap.client.ui.constants.IconType;

public class ExportButton extends com.github.gwtbootstrap.client.ui.Button {
		
	public ExportButton(String label) {
		super(label, IconType.DOWNLOAD_ALT);
	}
	
	public ExportButton() {
		this("Export");
	}
}
