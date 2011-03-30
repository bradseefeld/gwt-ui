package com.bradley.gwt.user.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ToolBar extends Composite {
	interface Binder extends UiBinder<Widget, ToolBar>{};
	
	@UiField
	protected Panel toolbar;
	
	public ToolBar() {
		Binder binder = GWT.create(Binder.class);
		initWidget(binder.createAndBindUi(this));
	}
	
	public void add(Widget button) {
		toolbar.add(button);
		toolbar.setVisible(true);
	}
}
