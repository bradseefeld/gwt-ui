package com.bradley.gwt.user.client.ui;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.IsWidget;

public class Mask {
	
	public static void mask(IsWidget element) {
		mask(element, null);
	}
	
	public static void mask(IsWidget element, String message) {
		_mask(element.asWidget().getElement(), message);
	}
	
	public static void unmask(IsWidget element) {
		_unmask(element.asWidget().getElement());
	}
	
	public static native void _mask(Element element, String message) /*-{
		$wnd.$(element).block({message:message});
	}-*/;
	
	public static native void _unmask(Element element) /*-{
	$wnd.$(element).unblock();
	}-*/;
}
