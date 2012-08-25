package com.bradley.gwt.user.client.ui;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.google.gwt.editor.client.LeafValueEditor;

/**
 * A Multiselect list that can be used with the Editor framework. 
 * 
 * @param <M> The model type that may correspond to T. M is what goes in.
 * @param <T> The type that is actually being edited. For example, if we are editing
 * 		a list of Roles but the property is actually a Set or String role IDs,
 * 		T would be String. M would be Role. In other words, T can be thought of
 * 		as what will come out when getValue() is called.
 */
public class MultiSelectSetBox<M, T> extends MultiSelectBox<M, T> implements LeafValueEditor<Set<T>> {

	@Override
	public void setValue(Set<T> value) {
		if (value == null) {
			super.setValue(null);
		} else {
			setValue(new LinkedList<T>(value));
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
		
		if (getItemCount() == 0 && lazySelectValues != null) {
			// Remote data never loaded.
			results.addAll(lazySelectValues);
			return results;
		}
		
		lazySelectValues = null;
		return results;
	}	
}
