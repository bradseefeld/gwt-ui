package com.bradley.gwt.user.client.ui;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A form field that has date values. This widget is enhanced with jquery date
 * picker. It also supports i18n through setting a DateTimeFormat.
 */
public class DateBox extends Composite implements HasValue<Date> {

	protected DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT);
	
	protected TextBox textbox;
	
	private static final Logger LOG = Logger.getLogger(DateBox.class.getName());
	
	public DateBox() {
		textbox = new TextBox();
		initWidget(textbox);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				enhance(textbox.getElement(), getJsPattern());
			}
		});
	}
	
	public void setFormat(DateTimeFormat format) {
		this.format = format;
		setFormat(textbox.getElement(), getJsPattern());
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
			LOG.warning("Users date was invalid: " + value);
			return null;
		}
	}

	@Override
	public void setValue(Date value) {
		textbox.setValue(format.format(value));
	}

	@Override
	public void setValue(Date value, boolean fireEvents) {
		setValue(value);
		
		// TODO: How do we call the handlers?
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Date> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
	
	/**
	 * Get the JS pattern for the date time format. JS date formats differ slightly
	 * so this method transcodes the format from Java style to JS style.
	 * 
	 * Note that its functionality is very limited. Currently we only handle year
	 * properties.
	 * 
	 * @return The JS style date format
	 */
	protected String getJsPattern() {
		String pattern = format.getPattern().toLowerCase();
		return pattern.replaceAll("yyyy", "yy");
	}
	
	private static native void enhance(Element el, String format)/*-{
		$wnd.$(el).datepicker({dateFormat: format});
	}-*/;
	
	private static native void setFormat(Element el, String format)/*-{
		$wnd.$(el).datepicker('option', 'dateFormat', format);
		$wnd.console.log(format);
	}-*/;
}
