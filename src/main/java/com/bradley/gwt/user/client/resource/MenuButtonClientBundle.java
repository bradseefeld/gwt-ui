package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;


public interface MenuButtonClientBundle extends ButtonResources {

	public interface MenuButtonStyle extends Style {
		String menuButton();
		
		String menu();
		
		String active();
		
		String activeItem();
	}

	@Source("menu-button.css")
	MenuButtonStyle style();
	
	@Source("image/down-arrow.gif")
	ImageResource downArrow();
	
	@Source("image/menu.gif")
	ImageResource menuBackground();
	
	@Source("image/menu-button-active-bg.png")
	ImageResource menuBackgroundActive();
	
	@Source("image/menu-item-active-bg.png")
	ImageResource menuItemBackgroundActive();
}
