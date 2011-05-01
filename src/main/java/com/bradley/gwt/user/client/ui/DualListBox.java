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
 * @param <M> The model type that may correspond to T. M is what goes in.
 * @param <T> The type that is actually being edited. For example, if we are editing
 * 		a list of Roles but the property is actually a Set or String role IDs,
 * 		T would be String. M would be Role. In other words, T can be thought of
 * 		as what will come out when getValue() is called.
 */
public class DualListBox<M, T> extends ListBox implements LeafValueEditor<Set<T>> {
	
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
	
	protected Set<T> lazySelectValues;
	
	/** Internal class level logger. */
	private static final Logger LOG = Logger.getLogger(DualListBox.class.getName());
	
	/**
	 * Default constructor.
	 */
	public DualListBox() {
		super(true);
		
		values = new HashMap<String, T>();
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
	
	@Override
	public void setValue(Set<T> values) {
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

	@Override
	public Set<T> getValue() {
		
		Set<T> results = new HashSet<T>();
		for (int i = 0; i < getItemCount(); i++) {
			if (isItemSelected(i)) {
				results.add(values.get(getValue(i)));
			}
		}
		
		lazySelectValues = null;
		
		LOG.fine("Dual list box selected values are: " + results.toString());
		return results;
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
		
		return "" + item.hashCode();
	}
}
