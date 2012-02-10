package com.bradley.gwt.logging.client;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.RemoteLogHandlerBase;
import com.google.gwt.logging.shared.RemoteLoggingService;
import com.google.gwt.logging.shared.RemoteLoggingServiceAsync;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * A logger that prevents duplicate log statements from being persisted to the client.
 * @author bseefeld
 *
 */
public class DedupedRemoteLogHandler extends RemoteLogHandlerBase {

	protected Set<String> records;

	protected AsyncCallback<String> callback;

	protected RemoteLoggingServiceAsync service;
	
	private static final int DEFAULT_CLEAR_CACHE_MILLIS = 300000; // Every 5 minutes

	class DefaultCallback implements AsyncCallback<String> {
		public void onFailure(Throwable caught) {
			wireLogger.severe("Remote logging failed: " + caught.toString());
		}

		public void onSuccess(String result) {
			if (result != null) {
				wireLogger.severe("Remote logging failed: " + result);
			} else {
				wireLogger.finest("Remote logging message acknowledged");
			}
		}
	}

	public DedupedRemoteLogHandler() {
		this(DEFAULT_CLEAR_CACHE_MILLIS);
	}
	
	public DedupedRemoteLogHandler(int clearCacheMillis) {
		setLevel(Level.WARNING); // Warn by default
		
		records = new HashSet<String>();
		service = (RemoteLoggingServiceAsync) GWT
				.create(RemoteLoggingService.class);
		callback = new DefaultCallback();
		
		Timer t = new Timer() {

			@Override
			public void run() {
				records.clear();
			}
		};
		t.scheduleRepeating(clearCacheMillis);
	}

	@Override
	public void publish(LogRecord record) {
		if (!isLoggable(record)) {
			return;
		}
		
		String key = createKey(record);
		if (records.contains(key)) {
			return;
		}
		
		records.add(key);
		service.logOnServer(record, callback);
	}
	
	protected String createKey(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append(record.getSourceClassName());
		sb.append(record.getSourceMethodName());
		return sb.toString();
	}
}
