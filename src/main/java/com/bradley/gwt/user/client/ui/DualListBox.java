package com.bradley.gwt.user.client.ui;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.ListBox;

/**
 * For now the type has to be of type String. We will refactor this as needed to
 * support more complex types.
 * 
 * @param <T> Must be of type string for now.
 */
public class DualListBox<T> extends ListBox implements LeafValueEditor<Set<T>> {

	public DualListBox() {
		super(true);
	}
	
	@Override
	public void setValue(Set<T> values) {
		if (values == null) {
			return;
		}
		
		for (T value : values) {
			for (int i = 0; i < getItemCount(); i++) {
				if (getValue(i).equals(value)) {
					setItemSelected(i, true);
				} else {
					setItemSelected(i, false);
				}
			}
		}
		
	}

	@Override
	public Set<T> getValue() {
		Logger log = Logger.getLogger(DualListBox.class.getName());
		Set<T> values = new HashSet<T>();
		for (int i = 0; i < getItemCount(); i++) {
			if (isItemSelected(i)) {
				log.info(getValue(i) + " was selected.");
				values.add((T) getValue(i));
			}
		}
		
		log.info(values.toString());
		
		return values;
	}
}
