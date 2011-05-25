package com.bradley.gwt.user.client.ui;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.user.client.ui.Widget;

public class HtmlList extends Widget {

	private final List<LIElement> listItems = new LinkedList<LIElement>();

	public static enum ListType {
		UNORDERED {
			public Element createElement() {
				return Document.get().createULElement();
			}
		},
		ORDERED {
			public Element createElement() {
				return Document.get().createOLElement();
			}
		};

		public abstract Element createElement();
	}

	public HtmlList(ListType listType) {
		setElement(listType.createElement());
	}

	public LIElement addItem(String text) {
		LIElement liElement = Document.get().createLIElement();
		liElement.setInnerText(text);
		liElement = addItem(liElement);

		listItems.add(liElement);

		return liElement;
	}

	public LIElement addItem(Element el) {
		LIElement liElement = Document.get().createLIElement();
		liElement.appendChild(el);
		return addItem(liElement);
	}

	/**
	 * Add a list item <li> to the list.
	 * 
	 * @param li List Item to add.
	 * @return
	 */
	public LIElement addItem(LIElement li) {
		getElement().appendChild(li);
		listItems.add(li);

		return li;
	}

	/**
	 * Remove all nodes from the list.
	 */
	public void empty() {
		Node child = getElement().getFirstChild();

		while (child != null) {
			getElement().removeChild(child);
			child = getElement().getFirstChild();
		}
	}
}