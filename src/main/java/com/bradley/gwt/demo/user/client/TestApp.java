package com.bradley.gwt.demo.user.client;

import java.util.Map;

import com.bradley.gwt.demo.user.client.entity.EmployeeProxy;
import com.bradley.gwt.demo.user.client.request.EmployeeRequest;
import com.bradley.gwt.demo.user.client.request.EmployeeRequestFactory;
import com.bradley.gwt.user.client.celltable.CellTableResources;
import com.bradley.gwt.user.client.celltable.PagingCellTablePanel;
import com.bradley.gwt.user.client.celltable.TextColumn;
import com.bradley.gwt.user.client.ui.CancelButton;
import com.bradley.gwt.user.client.ui.Dialog;
import com.bradley.gwt.user.client.ui.EditorPanel;
import com.bradley.gwt.user.client.ui.Notifier;
import com.bradley.gwt.user.client.ui.SaveButton;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class TestApp implements EntryPoint {

	@Override
	public void onModuleLoad() {

		// demoCellTableDialog();
		// demoNotifications();
		// demoButtons();
		demoDualListBox();
		//demoEditorPanel();
		demoMasking();
		demoDialog();
	}
	
	protected void demoDialog() {
		Dialog d = new Dialog("testing out modal", true);
		d.setWidget(new Label("this is a label widget"));
		d.show();
	}

	protected void demoMasking() {
		Panel element = new SimplePanel();
		element.add(new Label("test!"));
		//Mask.mask(element.getElement());
	}
	protected void demoEditorPanel() {
		EditorPanel editor = new EditorPanel();
		RootPanel.get().add(editor);
	}

	protected void demoDualListBox() {

		/*DualListBox<String, Object> list = new DualListBox<String>();
		list.addItem("Option 1", "value 1");
		list.addItem("Option 2", "value 2");
		list.addItem("Option 3", "value 3");
		RootPanel.get().add(list);*/
	}

	protected void demoButtons() {
		RootPanel.get().add(new SaveButton());
		RootPanel.get().add(new CancelButton());
	}

	protected void demoNotifications() {
		Notifier notifier = new Notifier();
		RootPanel.get().add(notifier);

		notifier.success("this is a test success message!");
		notifier.error("This is a test error message!");
		notifier.warn("This is a test warning message!");
		notifier.info("This is a test info message!");
	}

	protected void demoCellTableDialog() {
		CellTableResources resources = GWT.create(CellTableResources.class);
		CellTable<EmployeeProxy> table = new CellTable<EmployeeProxy>(15,
				resources);
		TextColumn<EmployeeProxy> nameColumn = new TextColumn<EmployeeProxy>() {

			@Override
			public String getValue(EmployeeProxy e) {
				return e.getFirstName();
			}
		};
		nameColumn.setSortable(true);

		table.addColumn(nameColumn, "First name");

		final EmployeeRequestFactory reqFactory = GWT
				.create(EmployeeRequestFactory.class);
		EventBus eventBus = new SimpleEventBus();

		PagingCellTablePanel<EmployeeProxy> panel = new PagingCellTablePanel<EmployeeProxy>() {

			@Override
			public void paginate(int offset, int limit, String sortColumn,
					boolean isSortAscending) {
				EmployeeRequest request = reqFactory.request();
				paginate(request.paginate(offset, limit, sortColumn,
						isSortAscending), offset);
			}

			public void count(Map<String, String> filters) {
				EmployeeRequest request = reqFactory.request();
				count(request.count());
			}
		};

		panel.initialize(table, reqFactory, eventBus);

		
	}

}
