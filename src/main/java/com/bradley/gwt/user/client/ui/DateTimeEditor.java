package com.bradley.gwt.user.client.ui;

import java.util.Date;
import java.util.logging.Logger;

import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DateTimeEditor extends Composite implements LeafValueEditor<Date>, HasChangeHandlers {

	@UiField
	DateBox date;
	
	@UiField
	TimeBox time;
	
	@UiField
	ControlLabel dateLabel;
	
	@UiField
	ControlLabel timeLabel;
	
	@UiField
	ControlGroup timeGroup;
	
	protected DateTimeFormat dateFormat;
	
	protected DateTimeFormat timeFormat;
	
	private static final Logger LOG = Logger.getLogger(DateTimeEditor.class.getName());
	
	public interface Binder extends UiBinder<Widget, DateTimeEditor> {
	}
	
	public DateTimeEditor() {
		super();
		Binder binder = GWT.create(Binder.class);
		initWidget(binder.createAndBindUi(this));		
	}
	
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		time.addChangeHandler(handler);
		return date.addChangeHandler(handler);
	}
	
	@Override
	public void setValue(Date value) {
		date.setValue(value);
		time.setValue(value);		
	}

	/**
	 * Get a Date in the browser local time.
	 * 
	 * @return The browser local time specified by user.
	 */
	@Override
	public Date getValue() {
		Date day = date.getValue();
		
		if (day == null) {
			return null;
		}
		
		Date time = this.time.getValue();
		
		if (time == null) {
			return day;
		}
		
		return DateTimeEditor.merge(day, time);
	}
	
	public DateBox getDateBox() {
		return date;
	}
	
	public TimeBox getTimeBox() {
		return time;
	}
	
	public ControlLabel getDateLabel() {
		return dateLabel;
	}
	
	public ControlLabel getTimeLabel() {
		return timeLabel;
	}
	
	public ControlGroup getTimeGroup() {
		return timeGroup;
	}
	
	public static Date merge(Date day, Date time) {
		LOG.finer("Raw time is " + time.getTime());
		
		long timeMillis = time.getTime() % (24 * 60 * 60 * 1000);
		int offsetMillis = new Date().getTimezoneOffset() * 60 * 1000;
		int dayMillis = 24 * 60 * 60 * 1000;
		if (timeMillis < offsetMillis) {
			// Need to add a day to time to account for day overflow
			timeMillis += dayMillis;
		} else if (timeMillis + offsetMillis > dayMillis) {
			//timeMillis -= dayMillis;
		}
		
		timeMillis -= offsetMillis;
		
		LOG.finer("Milliseconds into day is " + timeMillis);
		Date d = new Date(day.getTime() + timeMillis);
		return d;
	}
}
