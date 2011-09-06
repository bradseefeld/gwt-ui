package com.bradley.gwt.user.client.ui;

import java.util.logging.Logger;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.ListBox;

/**
 * An enhancement of a ListBox to support values other than String. A complex
 * object type can be used here as values. Additionally, this widget is visually
 * enhanced with jquery to be more usable. 
 * 
 * @param <T> The type that this list box displays.
 */
public class ComboBox<T> extends ListBox implements LeafValueEditor<T> {
	
	protected T lazyValue;
		
	/** Internal class level logger. */
	private static final Logger LOG = Logger.getLogger(ComboBox.class.getName());
	
	public ComboBox() {
		super();
	}
	
	public void addItem(String display) {
		super.addItem(display);
		
		if (lazyValue != null) {
			setValue(lazyValue);
		}
	}
	
	public void addItem(String display, String value) {
		super.addItem(display, value);
		
		if (lazyValue != null) {
			setValue(lazyValue);
		}
	}
	
	@Override
	public void setValue(T value) {
		
		if (value == null) {
			if (getItemCount() > 0) {
				setItemSelected(0, true);
			}
			return;
		}
		
		if (getItemCount() == 0) {
			lazyValue = value;
			LOG.fine("No values existed yet because we are probably waiting for " +
					"them to load from the server. The value will be set after" +
					"options have been set on this ComboBox.");
		}
				
		for (int i = 0; i < getItemCount(); i++) {
			if (getValue(i).equals(stringValue(value))) {
				setItemSelected(i, true);
				lazyValue = null;
				return;
			}
		}
	}

	@Override
	public T getValue() {
		if (getSelectedIndex() < 0) {
			return null;
		}
		
		lazyValue = null;
		
		String value = super.getValue(getSelectedIndex());
		return castStringValue(value);
	}
	
	/**
	 * Handle casting a string value to the respective complex object type. The
	 * parent list box only supports values as strings, so we allow child types
	 * to convert these strings to the correct object type.
	 * 
	 * @param value The string value.
	 * @return The complex object type.
	 */
	protected T castStringValue(String value) {
		return (T) value;
	}
	
	protected String stringValue(T value) {
		return value.toString();
	}
}
