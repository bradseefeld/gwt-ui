package com.bradley.gwt.user.client.ui;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.cell.client.Cell;

public class TextTitleColumnTest {

	protected TextTitleColumn<String> column;
	
	@Before
	public void setUp() {
		column = new TextTitleColumn<String>() {

			@Override
			public String getValue(String object) {
				return "test";
			}
		};
	}
	
	@Test
	public void testGetCell() {
		Cell<?> cell = column.getCell();
		assertTrue(cell instanceof TextTitleCell);
	}
}
