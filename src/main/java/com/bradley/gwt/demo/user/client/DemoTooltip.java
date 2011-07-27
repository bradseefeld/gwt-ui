package com.bradley.gwt.demo.user.client;

import com.bradley.gwt.user.client.ui.ToolTip;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.UIObject;

public class DemoTooltip extends ToolTip {

	public DemoTooltip(UIObject target) {
		super(target);
		
		add(new Label("this is the tooltip"));
	}
}
