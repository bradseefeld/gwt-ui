package com.bradley.gwt.user.client.ui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.google.gwt.user.client.ui.ListBox;

public class MultiSelectBox<M, T> extends ListBox {
	
	/**
	 * A map of html option value (String) to the type being set/get. This is 
	 * really a String repsentation of the values hashcode to the value.
	 */
	protected Map<String, T> values;
	
	/**
	 * A renderer that will transform complex objects into Strings for 
	 * presentation.
	 */
	protected Renderer<M> renderer;
	
	protected List<T> lazySelectValues;
	
	/** True if the widget has been enhanced by jquery. */
	protected boolean enhanced = false;
	
	/** Internal class level logger. */
	private static final Logger LOG = Logger.getLogger(MultiSelectSetBox.class.getName());
	
	/**
	 * Default constructor.
	 */
	public MultiSelectBox() {
		super(true);
		
		values = new HashMap<String, T>();
	}
	
	@Override
	public void clear() {
		values.clear();
		super.clear();
	}
	
	/**
	 * Add an option to the list.
	 * 
	 * @param display The text that is displayed in the list.
	 * @param value The value that is given back when this option is selected.
	 */
	public void addItem(String display, T value) {
		values.put(valueOf(value), value);
		super.addItem(display, valueOf(value));
		
		if (lazySelectValues != null) {
			setValue(lazySelectValues);
		}
	}
		
	/**
	 * Add an option to the list.
	 * 
	 * @param model The model that represents what will be displayed.
	 * @param value The value that is given back when this option is selected.
	 */
	public void addItem(M model, T value) {
		values.put(valueOf(value), value);
		super.addItem(render(model), valueOf(value));
		
		if (lazySelectValues != null) {
			setValue(lazySelectValues);
		}
	}
	
	public void setValue(List<T> values) {
		if (values == null) {
			return;
		}
		
		/*
		 * Sometimes the list of options is loaded lazily from the server. In this
		 * case, we may be setting the selected options before the list of options
		 * actually loads. To support this use case without requiring extra coding
		 * on the developers part, we will hold off on selection until items are
		 * added.
		 */
		if (getItemCount() == 0) {
			LOG.fine("Attempting to set values when no options exist. We will " +
					"hold off on setting the values until some options have been " +
					"added.");
			lazySelectValues = values;
		}
		
		for (int i = 0; i < getItemCount(); i++) {
			for (T value : values) {
				if (getValue(i).equals(valueOf(value))) {
					setItemSelected(i, true);
				}
			}
		}
	}
	
	/**
	 * Set the renderer responsible for generating a valid display value
	 * for the complex model type.
	 * 
	 * @param renderer
	 */
	public void setRenderer(Renderer<M> renderer) {
		this.renderer = renderer;
	}
	
	/**
	 * Remove all selected values.
	 * 
	 * @return The items removed.
	 */
	public Set<T> removeSelected() {
		Set<T> removed = new HashSet<T>();
		
		for (int i = getItemCount() - 1; i >= 0; i--) {
			if (isItemSelected(i)) {
				String key = getValue(i);
				removed.add(values.get(key));
				values.remove(key);
				removeItem(i);
			}
		}
		
		return removed;
	}
	
	/**
	 * Render the complex model into a simple String to be used in the list.
	 * 
	 * @param item
	 * @return
	 */
	protected String render(M item) {
		if (renderer == null) {
			return item.toString();
		}
		
		return renderer.render(item);
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
		
		return item.toString();
	}
}
