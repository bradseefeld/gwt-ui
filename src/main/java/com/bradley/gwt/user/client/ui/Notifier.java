package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.NotifierClientBundle;
import com.bradley.gwt.user.client.resource.NotifierClientBundle.NotifierCssResource;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;

/**
 * Provides a notification area to display messages to the user. This class should
 * probably be a Singleton when it is used. Use GIN to achieve singleton.
 */
public class Notifier extends NotificationMole {
	
	protected String currentMessage;
	
	protected static final NotifierCssResource css = NotifierClientBundle.INSTANCE.style();
	
	protected static final int HIDE_DELAY = 7000;
	
	private static Notifier instance;
	
	/**
	 * Default constructor.
	 */
	public Notifier() {
		css.ensureInjected();
		addStyleName(css.notifications());
		//setAnimationDuration(500);
	}
	
	public static Notifier getInstance() {
		
		if (instance == null) {
			instance = new Notifier();
		}
		
		return instance;
	}
	
	/**
	 * Display a success message.
	 * 
	 * @param msg The message to display as success.
	 */
	public void success(String msg) {
		clearStyles();
		addStyleName(css.success());
		show(msg);
		
	}
	
	/**
	 * Display an error message. Error messages require the attention and action
	 * of the user. For example, invalid form fields.
	 * 
	 * @param msg The message to display as an error.
	 * @return The panel that contains the message.
	 */
	public void error(String msg) {
		clearStyles();
		show(msg);
		addStyleName(css.error());
	}
	
	/**
	 * Display a warning message. Warning messages alert the user of something
	 * that has gone wrong, but action is optional. For example, warning the user
	 * that they will automatically be logged off in 1 minute.
	 * 
	 * @param msg The message to display as a warning.
	 */
	public void warn(String msg) {
		clearStyles();
		show(msg);
		addStyleName(css.warn());
	}
	
	/**
	 * Display a notification message. These are messages that are neither success
	 * or failure but are simply informative. For example, a notification regarding
	 * the actions of another dispatcher on the system.
	 * 
	 * @param msg The message to display as a notification.
	 */
	public void info(String msg) {
		clearStyles();
		show(msg);
		addStyleName(css.info());
	}
	
	public void show(final String message) {
		super.show(message);
		this.currentMessage = message;
		
		Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {

			@Override
			public boolean execute() {
				
				if (message.equals(currentMessage)) {
					hideNow();
				}
				
				return false;
			}
			
		}, HIDE_DELAY);
	}
	
	private void clearStyles() {
		removeStyleName(css.info());
		removeStyleName(css.success());
		removeStyleName(css.error());
		removeStyleName(css.warn());
	}
}
