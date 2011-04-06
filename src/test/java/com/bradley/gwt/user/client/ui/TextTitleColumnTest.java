package com.bradley.gwt.user.client.ui;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.bradley.gwt.user.client.celltable.TextColumn;
import com.bradley.gwt.user.client.celltable.TextTitleCell;
import com.google.gwt.cell.client.Cell;

public class TextTitleColumnTest {

	protected TextColumn<String> column;
	
	@Before
	public void setUp() {
		column = new TextColumn<String>() {

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
