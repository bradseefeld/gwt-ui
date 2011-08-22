package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.ToolBarClientBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ToolBar extends HTMLPanel implements HasWidgets {
	
	private static final ToolBarClientBundle resources = GWT.create(ToolBarClientBundle.class);
	
	public ToolBar(String html) {
		super("div", html);

		init();
	}
	
	public ToolBar(SafeHtml safe) {
		super(safe);
		init();
	}
	
	public ToolBar(String tag, String html) {
		super(tag, html);
		init();
	}
	
	public ToolBar() {
		this("");
	}
	
	public void add(Widget button) {
		super.add(button);
		setVisible(true);
	}
	
	protected void init() {
		addStyleName(resources.style().toolbar());
		resources.style().ensureInjected();
	}
}
