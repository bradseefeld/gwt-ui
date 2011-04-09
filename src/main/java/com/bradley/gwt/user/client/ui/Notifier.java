package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.UIClientBundle;
import com.bradley.gwt.user.client.resource.UICssResource;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Provides a notification area to display messages to the user. This class should
 * probably be a Singleton when it is used. Use GIN to achieve singleton.
 */
public class Notifier extends FlowPanel {

	/** Number of milliseconds before notifications disappear. */
	protected  int notificationDelay = 10000;
	
	/** The max number of notifications that should ever show. */
	protected static final int MAX_NOTIFICATIONS = 4;
	
	protected static final UICssResource css = UIClientBundle.INSTANCE.getUICssResource();
	
	/**
	 * Default constructor.
	 */
	public Notifier() {
		css.ensureInjected();
		addStyleName(css.notifications());
		addStyleName(css.hidden());
	}
	
	/**
	 * Display a success message.
	 * 
	 * @param msg The message to display as success.
	 */
	public void success(String msg) {
		
		Label label = new Label(msg);
		label.addStyleName(css.success());
		show(label);
	}
	
	/**
	 * Display an error message. Error messages require the attention and action
	 * of the user. For example, invalid form fields.
	 * 
	 * @param msg The message to display as an error.
	 */
	public void error(String msg) {
		Label label = new Label(msg);
		label.addStyleName(css.error());
		show(label, -1);
	}
	
	/**
	 * Display a warning message. Warning messages alert the user of something
	 * that has gone wrong, but action is optional. For example, warning the user
	 * that they will automatically be logged off in 1 minute.
	 * 
	 * @param msg The message to display as a warning.
	 */
	public void warn(String msg) {
		Label label = new Label(msg);
		label.addStyleName(css.warn());
		show(label);
	}
	
	/**
	 * Display a notification message. These are messages that are neither success
	 * or failure but are simply informative. For example, a notification regarding
	 * the actions of another dispatcher on the system.
	 * 
	 * @param msg The message to display as a notification.
	 */
	public void info(String msg) {
		Label label = new Label(msg);
		label.addStyleName(css.info());
		show(label);
	}
	
	protected void show(Label msg) {
		show(msg, notificationDelay);
	}
	
	protected void show(Label msg, int delay) {
		
		final Panel wrapper = new SimplePanel();
		wrapper.addStyleName(css.notification());
		wrapper.addStyleName(msg.getStyleName());		
		wrapper.add(msg);
		
		// Insert at the top
		insert(wrapper, 0);
		
		if (delay > 0) {
			// Remove message after a certain amount of time.
			Timer timer = new Timer() {
				
				@Override
				public void run() {
					remove(wrapper);
				}
			};
			timer.schedule(delay);
		}
		
		// Remove notifications as needed.
		while (getWidgetCount() > MAX_NOTIFICATIONS) {
			remove(getWidgetCount() - 1);
		}
		
		removeStyleName(css.hidden());
	}
}
