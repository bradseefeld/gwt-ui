package com.bradley.gwt.user.client.ui;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

public class TimeBox extends Composite implements HasValue<Date> {

	protected TextBox textbox;
	
	protected DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.TIME_SHORT);
	
	private static final Logger LOG = Logger.getLogger(TimeBox.class.getName());

	public TimeBox() {
		textbox = new TextBox();
		initWidget(textbox);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				enhance(getElement());
			}
		});
	}
	
	public void setFormat(DateTimeFormat format) {
		this.format = format;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Date> handler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getValue() {
		String value = textbox.getValue();
		if (value == null || value.isEmpty()) {
			return null;
		}
		
		try {
			return format.parse(value);
		} catch (Exception e) {
			LOG.warning("Unrecognized time: " + value);
			return null;
		}
	}

	@Override
	public void setValue(Date value) {
		if (value == null) {
			textbox.setValue("");
			return;
		}
		
		textbox.setValue(format.format(value));
	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		setValue(value);
		// TODO fire events!
	}

	private static native void enhance(Element el)/*-{
		$wnd.$(el).timepicker();
	}-*/;
}
