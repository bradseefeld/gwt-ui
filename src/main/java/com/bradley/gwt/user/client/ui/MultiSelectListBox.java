package com.bradley.gwt.user.client.ui;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.editor.client.LeafValueEditor;

public class MultiSelectListBox<M, T> extends MultiSelectBox<M, T> implements LeafValueEditor<List<T>> {

	@Override
	public List<T> getValue() {
		List<T> results = new LinkedList<T>();
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
