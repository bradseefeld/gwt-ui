package com.bradley.gwt.user.client.ui;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.ListBox;

/**
 * TODO: ListBox only supports Strings as values.
 * TODO: This will become like GXT Combobox... We want to add auto-complete abilities.
 * 
 * @param <T> The type that this list box displays.
 */
public class ComboBox<T> extends ListBox implements LeafValueEditor<T> {
		
	@Override
	public void setValue(T value) {
		
		for (int i = 0; i < getItemCount(); i++) {
			if (getValue(i).equals(value)) {
				setItemSelected(i, true);
				return;
			}
		}
	}

	@Override
	public T getValue() {
		return (T) super.getValue(getSelectedIndex());
	}

}
