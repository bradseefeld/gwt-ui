package com.bradley.gwt.user.client.ui;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.UIObject;

public class Mask {
	
	public static void mask(UIObject element) {
		mask(element, null);
	}
	
	public static void mask(UIObject element, String message) {
		_mask(element.getElement(), message);
	}
	
	public static void unmask(UIObject element) {
		_unmask(element.getElement());
	}
	
	public static native void _mask(Element element, String message) /*-{
		$wnd.$(element).block({message:message});
	}-*/;
	
	public static native void _unmask(Element element) /*-{
	$wnd.$(element).unblock();
	}-*/;
}
