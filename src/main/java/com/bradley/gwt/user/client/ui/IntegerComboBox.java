package com.bradley.gwt.user.client.ui;

public class IntegerComboBox extends ComboBox<Integer> {

	public void addItem(int display, int value) {
		addItem("" + display, "" + value);
	}
	
	protected Integer castStringValue(String value) {
		return Integer.parseInt(value);
	}
}
