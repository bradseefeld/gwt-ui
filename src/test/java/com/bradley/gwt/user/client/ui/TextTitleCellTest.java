package com.bradley.gwt.user.client.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.bradley.gwt.user.client.celltable.TextTitleCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class TextTitleCellTest {

	protected TextTitleCell cell;
	
	protected Context context;
	
	protected SafeHtmlBuilder builder;
	
	@Before
	public void setUp() {
		cell = new TextTitleCell();
		context = new Context(0, 0, "");
		builder = new SafeHtmlBuilder();
	}
	
	@Test
	public void testRender() {
		cell.render(context, "my value", builder);
		assertEquals("<div title=\"my value\">my value</div>", builder.toSafeHtml().asString());
	}
	
	@Test
	public void testRenderWithQuotes() {
		cell.render(context, "my \"value\"", builder);
		assertEquals("<div title=\"my &quot;value&quot;\">my &quot;value&quot;</div>", builder.toSafeHtml().asString());
	}
	
	@Test
	public void testRenderWithNull() {
		cell.render(context, null, builder);
		assertEquals("", builder.toSafeHtml().asString());
	}
	
	@Test
	public void testRenderWithEmptyString() {
		cell.render(context, "", builder);
		assertEquals("<div title=\"\"></div>", builder.toSafeHtml().asString());
	}
}
