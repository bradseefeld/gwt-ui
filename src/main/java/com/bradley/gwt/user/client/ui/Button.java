package com.bradley.gwt.user.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class Button extends Composite {

	interface Binder extends UiBinder<Widget, Button>{};
	
	protected static final UICssResource css = UIClientBundle.INSTANCE.getUICssResource();
	
	static {
		css.ensureInjected();
	}
	
	@UiField
	protected PushButton button;
	
	protected Command clickCommand;
	
	public Button(String label) {
		this(label, null, null);
	}
	
	public Button(String label, Command clickCommand) {
		this(label, null, clickCommand);
	}
	
	public Button(String label, ImageResource image) {
		this(label, image, null);
	}
	
	// Take in a label, image and command
	public Button(String label, ImageResource image, Command clickCommand) {
		Binder binder = GWT.create(Binder.class);
		initWidget(binder.createAndBindUi(this));
		
		this.clickCommand = clickCommand;
		
		StringBuilder html = new StringBuilder();
		
		if (image != null) {
			Element imgSpan = DOM.createSpan();
			Image img = new Image(image);
			imgSpan.setInnerHTML(img.toString());
			imgSpan.addClassName(css.buttonIcon());
			html.append(imgSpan.toString());
		}
		
		Element labelSpan = DOM.createSpan();
		labelSpan.addClassName(css.buttonLabel());
		labelSpan.setInnerText(label);
		html.append(labelSpan.toString());
		
		button.setHTML(html.toString());
	}
	
	/**
	 * Automatically called when the button is clicked.
	 * 
	 * @param e The click event.
	 */
	@UiHandler("button")
	protected void onClick(ClickEvent e) {
		clickCommand.execute();
	}
	
	@UiHandler("button")
	protected void onMouseOver(MouseOverEvent e) {
		button.addStyleName(css.buttonMouseOver());
	}
	
	@UiHandler("button")
	protected void onMouseOut(MouseOutEvent e) {
		button.removeStyleName(css.buttonMouseOver());
	}
}
