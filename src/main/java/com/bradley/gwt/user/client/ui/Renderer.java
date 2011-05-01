package com.bradley.gwt.user.client.ui;

public interface Renderer<T> {

	/**
	 * Render the object for output to a user. This is the process of converting
	 * a complex model type to a simple String for display.
	 * 
	 * @param object The object to render
	 * @return A string representation of the object.
	 */
	String render(T object);
}
