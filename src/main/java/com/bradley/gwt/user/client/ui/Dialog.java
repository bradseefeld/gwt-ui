package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.DialogClientBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Wrapper for a jquery dialog.
 */
public class Dialog implements HasOneWidget {

	protected Widget widget;
	
	protected boolean modal = false;
	
	protected String title;
	
	public Dialog(String title, boolean modal) {
		this(title, modal, (DialogClientBundle) GWT.create(DialogClientBundle.class));
	}
	
	public Dialog(String title) {
		this(title, false);
	}
	
	public Dialog(String title, boolean modal, DialogClientBundle resources) {
		this.title = title;
		this.modal = modal;
		
		resources.getStyle().ensureInjected();
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				setMinHeight(400);
				setMinWidth(600);
			}
		});
	}
	
	@Override
	public void setWidget(IsWidget w) {
		setWidget(w.asWidget());
	}

	@Override
	public Widget getWidget() {
		return widget;
	}

	@Override
	public void setWidget(Widget w) {
		this.widget = w;
		RootPanel.get().add(w);
		dialog(w.getElement(), title, modal);
	}
	
	/**
	 * Show the dialog.
	 */
	public void show() {
		if (widget == null) {
			throw new IllegalArgumentException("Widget has not been initialized yet.");
		}
		
		callDialogMethod(widget.getElement(), "open");
	}
	
	/**
	 * Hide the dialog.
	 */
	public void hide() {
		if (widget == null) {
			throw new IllegalArgumentException("Widget has not been initialized yet.");
		}
		
		callDialogMethod(widget.getElement(), "close");
	}
	
	/**
	 * Set the minimum width of the Dialog window. The widget will not be able to resized
	 * smaller than this width.
	 * 
	 * @param pixelWidth The smallest width in pixels.
	 */
	public void setMinWidth(int pixelWidth) {
		setMinWidth(widget.getElement(), pixelWidth);
	}
	
	public void setMinHeight(int pixelHeight) {
		setMinHeight(widget.getElement(), pixelHeight);
	}
	
	public void setWidth(int pixelWidth) {
		setWidth(widget.getElement(), pixelWidth);
	}
	
	protected static native void setWidth(Element el, int pixelWidth)/*-{
		$wnd.$(el).dialog("option", "width", pixelWidth);
	}-*/;
	
	protected static native void setMinHeight(Element el, int pixelHeight)/*-{
		$wnd.$(el).dialog("option", "minHeight", pixelHeight);
	}-*/;
	
	protected static native void setMinWidth(Element el, int pixelWidth)/*-{
		$wnd.$(el).dialog("option", "minWidth", pixelWidth);
	}-*/;
	
	protected static native void callDialogMethod(Element el, String method)/*-{
		$wnd.$(el).dialog(method);
	}-*/;
	
	protected static native void dialog(Element el, String title, boolean modal)/*-{
		$wnd.$(el).dialog({
			autoOpen: false,
			modal: modal,
			title: title,
			resizable: false,
			zIndex: 2600
		});
	}-*/;
}
