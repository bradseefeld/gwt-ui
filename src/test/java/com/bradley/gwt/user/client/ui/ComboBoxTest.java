package com.bradley.gwt.user.client.ui;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ComboBoxTest {

	@Test
	public void testSetValueWithNull() {
		ComboBox<String> box = new ComboBox<String>();
		box.setValue(null);
	}
}
