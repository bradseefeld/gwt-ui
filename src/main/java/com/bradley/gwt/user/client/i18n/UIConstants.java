package com.bradley.gwt.user.client.i18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;

public interface UIConstants extends Constants {
	
	public static final UIConstants INSTANCE = GWT.create(UIConstants.class);
	
	@DefaultStringValue("Save")
	String save();
	
	@DefaultStringValue("Cancel")
	String cancel();
	
	@DefaultStringValue("No data available")
	String noDataAvailable();
	
	@DefaultStringValue("Beging typing...")
	String beginTyping();
	
	@DefaultStringValue("Search")
	String search();

	@DefaultStringValue("Choose date...")
	String chooseDateDotDotDot();
	
	@DefaultStringValue("Choose time...")
	String chooseTimeDotDotDot();
}
