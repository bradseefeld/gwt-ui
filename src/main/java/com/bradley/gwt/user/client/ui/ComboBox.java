package com.bradley.gwt.user.client.ui;

import java.util.logging.Logger;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.ListBox;

/**
 * TODO: ListBox only supports Strings as values.
 * TODO: This will become like GXT Combobox... We want to add auto-complete abilities.
 * 
 * @param <T> The type that this list box displays.
 */
public class ComboBox<T> extends ListBox implements LeafValueEditor<T> {
	
	protected T lazyValue;
	
	/** Internal class level logger. */
	private static final Logger LOG = Logger.getLogger(ComboBox.class.getName());

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
		
		if (getItemCount() == 0) {
			lazyValue = value;
			LOG.fine("No values existed yet because we are probably waiting for " +
					"them to load from the server. The value will be set after" +
					"options have been set on this ComboBox.");
		}
		
		for (int i = 0; i < getItemCount(); i++) {
			if (getValue(i).equals(value)) {
				setItemSelected(i, true);
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
		
		return (T) super.getValue(getSelectedIndex());
	}
}
