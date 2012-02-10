package com.bradley.gwt.user.client.google.analytics;

public final class GoogleAnalyticsUtil {
	
	public static native void trackEvent(String category, String action, String label) /*-{
    	$wnd._gaq.push(['_trackEvent', category, action, label]);
	}-*/;

	public static native void trackEvent(String category, String action, String label, int intArg) /*-{
    	$wnd._gaq.push(['_trackEvent', category, action, label, intArg]);
	}-*/;
}
