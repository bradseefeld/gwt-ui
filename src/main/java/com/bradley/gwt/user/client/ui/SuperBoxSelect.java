package com.bradley.gwt.user.client.ui;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.user.client.Element;

public class SuperBoxSelect<T> extends ComboBox<T> {

	/**
	 * Fired when the user has added a selection.
	 */
	//public static final EventType AddSelection = new EventType();

	/**
	 * Fired when the user has removed a selection.
	 */
	//public static final EventType RemoveSelection = new EventType();

	/**
	 * The models that have been currently selected.
	 */
	protected Set<T> selections = new HashSet<T>();

	/**
	 * An unordered list.
	 */
	protected HtmlList list;

	/**
	 * Template used to display selections. Tells us how to display
	 * the models that have been selected.
	 */
	//protected XTemplate selectionTemplate;

	/**
	 * The GWT element representing the input field.
	 */
	private Element input;

	/**
	 * We manually manage when to display the empty text.
	 */
	protected String usersEmptyText;

	public SuperBoxSelect() {
		super();
/*
		addListener(Events.Select, new Listener<FieldEvent>() {

			@Override
			public void handleEvent(FieldEvent be) {
				addSelection(getValue());
				setValue(null);
			}
		});*/
/*
		addListener(Events.Render, new Listener<ComponentEvent>() {

			@Override
			public void handleEvent(ComponentEvent be) {
				onAfterRender();
			}
		});*/
	}

	public void clear() {
		super.clear();

		selections.removeAll(selections);
		buildList();
	}

	public void setEmptyText(String emptyText) {
		//super.setEmptyText(emptyText);
		this.usersEmptyText = emptyText;
	}

	public void addSelection(T selection) {
		boolean added = selections.add(selection);

		if (added) {
			buildList();
			//fireEvent(AddSelection, new AppEvent(AddSelection, selection));
		}
	}

	public void removeSelection(T selection) {
		boolean removed = selections.remove(selection);

		if (removed) {
			buildList();
			//fireEvent(RemoveSelection, new AppEvent(RemoveSelection, selection));
		}
	}

	public Set<T> getSelections() {
		return selections;
	}

	/**
	 * Set the template used to display selections in the superbox. This
	 * is different than the ComboBox template.
	 * 
	 * @param template
	 */
	public void setSelectionTemplate(String template) {
		//setSelectionTemplate(XTemplate.create(template));
	}

	/*public void setSelectionTemplate(XTemplate template) {
		//assertPreRender();
		//selectionTemplate = template;
	}*/

	/**
	 * Modify the parent's DOM a little after rendering. We
	 * alter the structure of the ComboBox a little to suit
	 * or needs. This method automatically called after the
	 * widget has rendered.
	 */
	/*protected void onAfterRender() {

		// Add some styling to our parent div.
		addStyleName("schedgy-superboxselect");
		addStyleName("schedgy-superboxselect-display-btns");
		addStyleName("x-form-text"); // Highlights the top of the field.

		// Get the input field. It already exists in the DOM.
		input = getElement().getFirstChildElement();
		getElement().removeChild(input);
		input.addClassName("schedgy-superboxselect-input-field");
		input.setAttribute("style", "width:" + getElement().getClientWidth() + "px;");

		// Add a list for containing our selections and input field.
		list = new HtmlList(ListType.UNORDERED);

		// Add our list to the DOM.
		getElement().appendChild(list.getElement());

		Element expand = getElement().getFirstChildElement();
		getElement().removeChild(expand);

		Element buttons = Document.get().createDivElement();
		buttons.addClassName("schedgy-superboxselect-btns");

		expand = Document.get().createDivElement();
		expand.addClassName("schedgy-superboxselect-btn-expand");

		Event.sinkEvents(expand, Event.ONCLICK);
		Event.setEventListener(expand, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				if (event.getTypeInt() != Event.ONCLICK) {
					return;
				}

				if (isExpanded()) {
					collapse();
				} else {
					expand(); 
				}
			}
		});
		buttons.appendChild(expand);
		getElement().appendChild(buttons);

		// Setup the selection template.
		if (selectionTemplate == null) {
			selectionTemplate = XTemplate.create("{" + getDisplayField() + "}");
		}
		buildList();
	}*/

	/**
	 * Add the necassary list items to the field. These are the existing
	 * values in the form. This method is called automatically onRender and
	 * when a value is added or removed.
	 */
	protected void buildList() {
		if (list == null) {
			return;
		}

		list.empty();

		Iterator<T> iter = selections.iterator();

		/*while (iter.hasNext()) {
			final T model = iter.next();

			final LIElement li = list.addItem((String) selectionTemplate.applyTemplate(Util.getJsObject(model)));
			li.addClassName("schedgy-superboxselect-item");

			Anchor a = new Anchor();
			a.addStyleName("schedgy-superboxselect-item-close");
			a.sinkEvents(Event.ONCLICK);
			li.appendChild(a.getElement());

			// Setup click handler.
			Event.sinkEvents(a.getElement(), Event.ONCLICK);
			DOM.setEventListener(a.getElement(), new EventListener() {

				@Override
				public void onBrowserEvent(Event event) {
					removeSelection(model);
				}
			});

			// Setup the hover handler
			Event.sinkEvents(li, Event.ONMOUSEOVER | Event.ONMOUSEOUT);
			Event.setEventListener(li, new EventListener() {

				@Override
				public void onBrowserEvent(Event event) {
					if (event.getTypeInt() == Event.ONMOUSEOVER) {
						li.addClassName("schedgy-superboxselect-item-hover");
					}

					if (event.getTypeInt() == Event.ONMOUSEOUT) {
						li.removeClassName("schedgy-superboxselect-item-hover");
					}
				}
			});
		}

		LIElement li = list.addItem(input);
		li.addClassName("schedgy-superboxselect-input");

		if (selections.size() > 0) {
			super.setEmptyText(null);
		} else {
			super.setEmptyText(usersEmptyText);
		}*/
	}
}