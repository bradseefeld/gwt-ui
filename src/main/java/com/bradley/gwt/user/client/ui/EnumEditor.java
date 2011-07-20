package com.bradley.gwt.user.client.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RadioButton;

public class EnumEditor<E> extends FlowPanel implements LeafValueEditor<E> {

	protected String groupName;
	
    protected Map<RadioButton, E> map;

    @UiConstructor
    public EnumEditor(String groupName) {
        map = new HashMap<RadioButton, E>();
        this.groupName = groupName;
    }
    
    public void add(E value, String label) {
    	RadioButton rb = new RadioButton(groupName, label);
    	map.put(rb, value);
    	super.add(rb);
    }

    @Override
    public void setValue(E value) {
        if (value == null) {
            return;
        }
        
        for (RadioButton rb : map.keySet()) {
        	if (map.get(rb) == value) {
        		rb.setValue(true);
        		return;
        	}
        }
    }

    @Override
    public E getValue() {
        for (Entry<RadioButton, E> e: map.entrySet()) {
            if (e.getKey().getValue())
                return e.getValue();
        }
        return null;
    }

}
