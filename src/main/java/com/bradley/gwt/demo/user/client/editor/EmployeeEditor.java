package com.bradley.gwt.demo.user.client.editor;

import com.bradley.gwt.demo.user.client.entity.EmployeeProxy;
import com.bradley.gwt.user.client.ui.Button;
import com.bradley.gwt.user.client.ui.EditorPanel;
import com.bradley.gwt.user.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

public class EmployeeEditor extends EditorPanel<EmployeeProxy> {

	@UiField
	TextBox firstName;
	
	@UiField
	Button save;
	
	public interface Binder extends UiBinder<Widget, EmployeeEditor> {
	}
	
	public interface Driver extends RequestFactoryEditorDriver<EmployeeProxy, EmployeeEditor> {
	}
	
	public EmployeeEditor() {
		Binder binder = GWT.create(Binder.class);
		initWidget(binder.createAndBindUi(this));
	}
	
	public Button getSaveButton() {
		return save;
	}
}
