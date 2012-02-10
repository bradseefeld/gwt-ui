package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.ExportButtonClientBundle;
import com.google.gwt.core.client.GWT;

public class ExportButton extends Button {
	
	private static final ExportButtonClientBundle resources = GWT.create(ExportButtonClientBundle.class);
	
	public ExportButton(String label) {
		super(label, resources);
	}
	
	public ExportButton() {
		this("Export");
	}
}
