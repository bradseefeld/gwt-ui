package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.ButtonResources;
import com.bradley.gwt.user.client.resource.MenuButtonClientBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuButton extends FlowPanel {
	
	protected Panel menu;
	
	protected Button button;
		
	private static final MenuButtonClientBundle resources = GWT.create(MenuButtonClientBundle.class);
	
	public MenuButton(String label) {
		this(label, (ButtonResources) resources);
	}
	
	public MenuButton(String label, ButtonResources resources) {
		MenuButton.resources.style().ensureInjected();
		resources.style().ensureInjected();
		
		addStyleName(MenuButton.resources.style().menuButton());
		
		initButton(label, resources);
		initMenu();
		
		// Add a span for the down arrow.
		button.getElement().appendChild(DOM.createSpan());
	}
	
	public void add(final Label item) {
		menu.add(item);
		
		// Hide the menu once a choice is clicked.
		item.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				menu.setVisible(false);
				button.removeStyleName(resources.style().active());
			}
		});
		
		item.addMouseOverHandler(new MouseOverHandler() {

			@Override
			public void onMouseOver(MouseOverEvent event) {
				item.addStyleName(resources.style().activeItem());
			}
		});
		
		item.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent event) {
				item.removeStyleName(resources.style().activeItem());
			}
		});
	}
	
	public void onButtonClick() {
		// Show menu
		
		menu.setVisible(!menu.isVisible());
		
		if (menu.isVisible()) {
			button.addStyleName(resources.style().active());
		} else {
			button.removeStyleName(resources.style().active());
		}
	}
	
	private void initButton(String label, ButtonResources resources) {
		button = new Button(label, resources) {
			
			@Override
			public void onClick() {
				onButtonClick();
			}
		};
		
		add(button);
	}
	
	private void initMenu() {
		menu = new VerticalPanel();
		menu.addStyleName(resources.style().menu());
		menu.setVisible(false);		
		add(menu);
	}
}
