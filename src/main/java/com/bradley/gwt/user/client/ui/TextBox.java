package com.bradley.gwt.user.client.ui;

import com.google.gwt.dom.client.Element;

public class TextBox extends com.google.gwt.user.client.ui.TextBox {

	public TextBox() {
		super();
	}
	
	public TextBox(Element el) {
		super(el);
	}
	
	public void setEmptyText(String emptyText) {
		getElement().setAttribute("placeholder", emptyText);
	}
}
