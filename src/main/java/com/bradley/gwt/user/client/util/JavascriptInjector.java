package com.bradley.gwt.user.client.util;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.ScriptElement;

public class JavascriptInjector {

	private static HeadElement head;
	
	public static void inject(String javascript) {
		HeadElement head = getHead();
		ScriptElement element = createScriptElement();
		element.setText(javascript);
		head.appendChild(element);
	}
	
	private static ScriptElement createScriptElement() {
		ScriptElement script = Document.get().createScriptElement();
		script.setAttribute("language", "javascript");
		return script;
	}
	
	private static HeadElement getHead() {
		if (head != null) {
			return head;
		}
		
		Element element = Document.get().getElementsByTagName("head").getItem(0);
		assert element != null : "HTML head element required";
		head = HeadElement.as(element);
		return head;
	}
}
