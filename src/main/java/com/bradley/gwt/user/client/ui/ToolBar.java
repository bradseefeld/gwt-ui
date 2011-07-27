package com.bradley.gwt.user.client.ui;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ToolBar extends Composite implements HasWidgets {
	
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

	@Override
	public void clear() {
		toolbar.clear();
		
	}

	@Override
	public Iterator<Widget> iterator() {
		return toolbar.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return toolbar.remove(w);
	}
}
