package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.ToolTipClientBundle;
import com.bradley.gwt.user.client.util.JavascriptInjector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;

public class ToolTip extends FlowPanel implements HasWidgets {

	protected static int counter = 0;
	
	private static boolean isInjected = false;
	
	public ToolTip(UIObject target) {
		this(target, (ToolTipClientBundle) GWT.create(ToolTipClientBundle.class));
	}
	
	public ToolTip(final UIObject target, ToolTipClientBundle resources) {
		
		if (!isInjected) {
			JavascriptInjector.inject(resources.tooltips().getText());
			isInjected = true;
		}
		
		resources.style().ensureInjected();
		addStyleName(resources.style().tooltip());
		
		getElement().setId("tooltip-" + counter);
		counter++;
		
		RootPanel.get().add(this);
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				tooltip(target.getElement(), getElement().getId());
			}
		});
	}
	
	public void hide() {
		_hide(getElement().getId());
	}
	
	public void show() {
		_show(getElement().getId());
	}
	
	protected static native void _show(String tooltip) /*-{
		$wnd.$("#" + tooltip).show();
	}-*/;
	
	protected static native void _hide(String tooltip) /*-{
		$wnd.$("#" + tooltip).hide();
	}-*/;
	
	protected static native void tooltip(Element target, String tooltip)/*-{
		tooltip = "#" + tooltip;
		//$wnd.$(tooltip).insertAfter($wnd.$(target));
		$wnd.$(target).tooltip({
			tip: tooltip,
			predelay: 500, // Millis
			delay: 200
		}).dynamic({ bottom: { direction: 'down', bounce: true } });
	}-*/;
}
