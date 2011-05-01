package com.bradley.gwt.user.client.ui;

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
		this.title = title;
		this.modal = modal;
	}
	
	public Dialog(String title) {
		this(title, false);
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
	
	public void show() {
		if (widget == null) {
			throw new IllegalArgumentException("Widget has not been initialized yet.");
		}
		
		callDialogMethod(widget.getElement(), "open");
	}
	
	public void hide() {
		if (widget == null) {
			throw new IllegalArgumentException("Widget has not been initialized yet.");
		}
		
		callDialogMethod(widget.getElement(), "close");
	}
	
	protected static native void callDialogMethod(Element el, String method)/*-{
		$wnd.$(el).dialog(method);
	}-*/;
	
	protected static native void dialog(Element el, String title, boolean modal)/*-{
		$wnd.$(el).dialog({
			autoOpen: false,
			hide: "slide",
			modal: modal,
			title: title
		});
	}-*/;

}
