package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.UIClientBundle;
import com.bradley.gwt.user.client.resource.UICssResource;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

public class Button extends PushButton {

	//interface Binder extends UiBinder<Widget, Button>{};
	
	protected static final UICssResource css = UIClientBundle.INSTANCE.getUICssResource();
	
	static {
		css.ensureInjected();
	}
	
	@UiField
	protected PushButton button;
		
	public Button() {
		
	}
	
	public Button(String label) {
		this(label, null);
	}
	
	public Button(String label, ImageResource image) {
		
				
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
		
		setHTML(html.toString());
	}
	
	// TODO: Implement!
	public void setIcon() {
		
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
