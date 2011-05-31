package com.bradley.gwt.user.client.ui;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.bradley.gwt.user.client.i18n.UIConstants;
import com.bradley.gwt.user.client.resource.SuperBoxSelectResources;
import com.bradley.gwt.user.client.ui.HtmlList.ListType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * A TextBox that can have multiple values and has auto-complete behavior. Auto-complete
 * can be wired up via:
 * 
 * <code>
 * 		select.getInput().addKeyUpHandler(new KeyUpHandler() {
 *
 *			@Override
 *			public void onKeyUp(KeyUpEvent event) {
 *				select.setAutoCompleteSelections(list);
 *			}
 *		});
 * </code>
 *
 * @param <T>
 */
public class SuperBoxSelect<T> extends FlowPanel implements LeafValueEditor<Set<T>> {

	public interface ValueAddedHandler<T> {
		void onValueAdded(T value);
	}
	
	/**
	 * The models that have been currently selected.
	 */
	protected Set<T> selections = new HashSet<T>();

	/**
	 * An unordered list.
	 */
	protected HtmlList selectionList;
	
	protected HtmlList autoCompleteList;

	/**
	 * The GWT element representing the input field.
	 */
	private TextBox input;

	/**
	 * We manually manage when to display the empty text.
	 */
	protected String emptyText = UIConstants.INSTANCE.beginTyping();
	
	/** Responsible for rendering selections. */
	protected Renderer<T> renderer;
	
	protected Set<ValueAddedHandler<T>> addHandlers = new HashSet<ValueAddedHandler<T>>();
	
	protected SuperBoxSelectResources resources = GWT.create(SuperBoxSelectResources.class);

	public SuperBoxSelect() {
		super();
		
		// Add a list for containing our selections and input field.
		selectionList = new HtmlList(ListType.UNORDERED);
		selectionList.addStyleName(resources.getSuperBoxSelectCss().superBoxSelect());
		add(selectionList);
		
		autoCompleteList = new HtmlList(ListType.UNORDERED);
		autoCompleteList.addStyleName(resources.getSuperBoxSelectCss().autoComplete());
		autoCompleteList.addStyleName(resources.getSuperBoxSelectCss().hidden());
		add(autoCompleteList);
		
		input = new TextBox();
		initInput();
		
		resources.getSuperBoxSelectCss().ensureInjected();
			
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				buildList();
				input.setFocus(true);
			}
		});
	}
	
	public void addValueAddedHandler(ValueAddedHandler<T> h) {
		addHandlers.add(h);
	}
	
	public TextBox getInput() {
		return input;
	}

	public void clear() {
		selections.removeAll(selections);
		buildList();
	}

	public void setEmptyText(String emptyText) {
		if (this.emptyText.equals(input.getValue())) {
			input.setValue(emptyText);
		}
		this.emptyText = emptyText;
	}

	public void addSelection(T selection) {

		if (selections.add(selection)) {
			buildList();
			
			for (ValueAddedHandler<T> h : addHandlers) {
				h.onValueAdded(selection);
			}
		}
	}
	
	public void setAutoCompleteSelections(Set<T> selections) {
		autoCompleteList.empty();
		
		if (selections == null) {
			autoCompleteList.addStyleName(resources.getSuperBoxSelectCss().hidden());
			return;
		}
		
		autoCompleteList.removeStyleName(resources.getSuperBoxSelectCss().hidden());
		
		for (final T selection : selections) {
			String display = selection.toString();
			if (renderer != null) {
				display = renderer.render(selection);
			}
			
			final Anchor a = new Anchor();
			a.setText(display);
			RootPanel.get().add(a);
			autoCompleteList.addItem(a.getElement());
			
			a.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					addSelection(selection);
					setAutoCompleteSelections(new HashSet<T>());
					input.setValue("");
				}
			});
			
			a.addMouseOverHandler(new MouseOverHandler() {

				@Override
				public void onMouseOver(MouseOverEvent event) {
					a.addStyleName(resources.getSuperBoxSelectCss().autoCompleteItemHover());
				}
			});
			
			a.addMouseOutHandler(new MouseOutHandler() {

				@Override
				public void onMouseOut(MouseOutEvent event) {
					a.removeStyleName(resources.getSuperBoxSelectCss().autoCompleteItemHover());
				}
			});
		}
	}

	public void removeSelection(T selection) {
		boolean removed = selections.remove(selection);

		if (removed) {
			buildList();
		}
	}
	
	/**
	 * Set the renderer used to display selections in the super-box.
	 * 
	 * @param renderer The entity responsible for rendering selections.
	 */
	public void setRenderer(Renderer<T> renderer) {
		this.renderer = renderer;
	}

	/**
	 * Add the necassary list items to the field. These are the existing
	 * values in the form. This method is called automatically onRender and
	 * when a value is added or removed.
	 */
	protected void buildList() {
		if (selectionList == null) {
			return;
		}

		selectionList.empty();

		Iterator<T> iter = selections.iterator();

		while (iter.hasNext()) {
			final T model = iter.next();
			
			String display = model.toString();
			if (renderer != null) {
				display = renderer.render(model);
			}
			final LIElement li = selectionList.addItem(display);
			li.addClassName(resources.getSuperBoxSelectCss().selection());

			Anchor a = new Anchor();
			a.addStyleName(resources.getSuperBoxSelectCss().remove());
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

		RootPanel.get().add(input); // Add to DOM so that listeners are bound.
		selectionList.addItem(input.getElement());
	}

	@Override
	public void setValue(Set<T> values) {
		clear();
		if (values == null) {
			return;
		}
		
		for (T value : values) {
			addSelection(value);
		}
	}

	@Override
	public Set<T> getValue() {
		return selections;
	}
	
	protected void initInput() {
		input.setValue(emptyText);
		input.addStyleName(resources.getSuperBoxSelectCss().empty());
		
		// Clear empty text on focus.
		input.addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				input.removeStyleName(resources.getSuperBoxSelectCss().empty());
				if (emptyText.equals(input.getValue())) {
					input.setValue("");
				}
			}
		});
		
		// Add empty text on unfocus
		input.addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				if (input.getValue() == null || input.getValue().isEmpty()) {
					input.addStyleName(resources.getSuperBoxSelectCss().empty());
					input.setValue(emptyText);
				}
				
				// A little bit of a hack!
				// TODO: Find a better solution for detecting blur and hiding auto-complete
				Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {

					@Override
					public boolean execute() {
						setAutoCompleteSelections(null);
						return false;
					}
				}, 1000);
			}
		});
	}
}