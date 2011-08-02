package com.bradley.gwt.user.client.ui;

import java.util.Iterator;

import com.bradley.gwt.user.client.resource.ToolBarClientBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ToolBar extends Composite implements HasWidgets {
		
	protected Panel toolbar;
	
	private static final ToolBarClientBundle resources = GWT.create(ToolBarClientBundle.class);
	
	public ToolBar() {
		
		toolbar = new FlowPanel();
		toolbar.addStyleName(resources.style().toolbar());
		initWidget(toolbar);
		
		resources.style().ensureInjected();
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
