package com.bradley.gwt.user.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.TextBox;

public class TimeBox extends TextBox {

	public TimeBox() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				enhance(getElement());
			}
		});
	}
	
	public static native void enhance(Element el)/*-{
		$wnd.$(el).timepicker();
	}-*/;

}
