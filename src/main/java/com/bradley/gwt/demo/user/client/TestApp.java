package com.bradley.gwt.demo.user.client;

import java.util.Map;

import com.bradley.gwt.demo.user.client.entity.EmployeeProxy;
import com.bradley.gwt.demo.user.client.request.EmployeeRequest;
import com.bradley.gwt.demo.user.client.request.EmployeeRequestFactory;
import com.bradley.gwt.user.client.celltable.CellTableResources;
import com.bradley.gwt.user.client.celltable.PagingCellTablePanel;
import com.bradley.gwt.user.client.celltable.TextColumn;
import com.bradley.gwt.user.client.ui.ScrollableDialogBox;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.CellTable;

public class TestApp implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		CellTableResources resources = GWT.create(CellTableResources.class);
		CellTable<EmployeeProxy> table = new CellTable<EmployeeProxy>(15, resources);
		TextColumn<EmployeeProxy> nameColumn = new TextColumn<EmployeeProxy>() {

			@Override
			public String getValue(EmployeeProxy e) {
				return e.getFirstName();
			}
		};
		nameColumn.setSortable(true);
		
		table.addColumn(nameColumn, "First name");
		
		final EmployeeRequestFactory reqFactory = GWT.create(EmployeeRequestFactory.class);
		EventBus eventBus = new SimpleEventBus();
		
		
		PagingCellTablePanel<EmployeeProxy> panel = new PagingCellTablePanel<EmployeeProxy>() {

			@Override
			public void paginate(int offset, int limit, String sortColumn,
					boolean isSortAscending) {
				EmployeeRequest request = reqFactory.request();		
				paginate(request.paginate(offset, limit, sortColumn, isSortAscending), offset);
			}
			
			public void count(Map<String, String> filters) {
				EmployeeRequest request = reqFactory.request();
				count(request.count());
			}
		};
		
		panel.initialize(table, reqFactory, eventBus);
		
		ScrollableDialogBox box = new ScrollableDialogBox();
		box.setWidget(panel);
		//box.setSizePx(400, 300);
		box.setText("Employees");		
		box.show();
		
		//RootPanel.get().add(panel);
	}

}
