package com.bradley.gwt.user.client.ui;

import java.util.Date;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DateTimeEditor extends Composite implements LeafValueEditor<Date> {

	@UiField
	DateBox date;
	
	@UiField
	TimeBox time;
	
	@UiField
	Label dateLabel;
	
	@UiField
	Label timeLabel;
	
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
	
	@Override
	public void setValue(Date value) {
		date.setValue(value);
		time.setValue(value);
		
	}

	@Override
	public Date getValue() {
		Date day = date.getValue();
		Date time = this.time.getValue();
		if (time == null) {
			return day;
		}
		
		long timeMillis = time.getTime() % (24 * 60 * 60 * 1000);
		Date d = new Date(day.getTime() + timeMillis);
		return d;
	}
	
	public DateBox getDateBox() {
		return date;
	}
	
	public TimeBox getTimeBox() {
		return time;
	}
	
	public Label getDateLabel() {
		return dateLabel;
	}
	
	public Label getTimeLabel() {
		return timeLabel;
	}
}
