package com.bradley.gwt.user.client.google.analytics;

import com.google.gwt.core.client.GWT;

public final class GoogleAnalyticsUtil {
	
	public static void trackEvent(String category, String action, String label) {
		if (!GWT.isProdMode()) {
			return;
		}
		
		_trackEvent(category, action, label);
	}
	
	public static void trackEvent(String category, String action, String label, int intArg) {
		if (!GWT.isProdMode()) {
			return;
		}
		
		_trackEvent(category, action, label, intArg);
	}
	
	private static native void _trackEvent(String category, String action, String label) /*-{
    	$wnd._gaq.push(['_trackEvent', category, action, label]);
	}-*/;

	private static native void _trackEvent(String category, String action, String label, int intArg) /*-{
    	$wnd._gaq.push(['_trackEvent', category, action, label, intArg]);
	}-*/;
}
