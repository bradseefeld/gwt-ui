package com.bradley.gwt.user.client.ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.user.client.ui.ListBox;

/**
 * A Multiselect list that can be used with the Editor framework. 
 * 
 * TODO: Make this widget sexy. Its not actually a DualList yet. We want dual list!
 * Jquery has a pretty cool plugin for this... maybe we use that? Something like:
 * 
 * http://www.erichynds.com/jquery/jquery-ui-multiselect-widget/
 * http://quasipartikel.at/multiselect/
 * 
 * @param <T> The type to display in the list box
 */
public class DualListBox<T> extends ListBox implements LeafValueEditor<Set<T>> {
	
	/** A map of html option value (String) to complex object type. */
	protected Map<String, T> values;
	
	/** Internal class level logger. */
	private static final Logger LOG = Logger.getLogger(DualListBox.class.getName());
	
	/**
	 * Default constructor.
	 */
	public DualListBox() {
		super(true);
		
		values = new HashMap<String, T>();
	}
	
	public void addItem(T item) {
		values.put(valueOf(item), item);
		addItem(item.toString(), "" + item.hashCode());
	}
	
	@Override
	public void setValue(Set<T> values) {
		if (values == null) {
			return;
		}
		
		for (int i = 0; i < getItemCount(); i++) {
			for (T value : values) {
				if (getValue(i).equals(valueOf(value))) {
					setItemSelected(i, true);
				}
			}
		}
	}

	@Override
	public Set<T> getValue() {
		
		Set<T> results = new HashSet<T>();
		for (int i = 0; i < getItemCount(); i++) {
			if (isItemSelected(i)) {
				results.add(values.get(getValue(i)));
			}
		}
		
		LOG.fine("Dual list box selected values are: " + results.toString());
		return results;
	}
	
	/**
	 * Convert this value to a simple String. Should be unique per item given.
	 * 
	 * @param item
	 * @return
	 */
	protected String valueOf(T item) {
		if (item == null) {
			return "";
		}
		
		return "" + item.hashCode();
	}
}
