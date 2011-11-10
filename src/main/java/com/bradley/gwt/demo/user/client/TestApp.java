package com.bradley.gwt.demo.user.client;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.bradley.gwt.demo.user.client.editor.EmployeeEditor;
import com.bradley.gwt.demo.user.client.entity.EmployeeProxy;
import com.bradley.gwt.demo.user.client.request.EmployeeRequestFactory;
import com.bradley.gwt.user.client.animation.MoveAnimation;
import com.bradley.gwt.user.client.celltable.CellTable;
import com.bradley.gwt.user.client.celltable.PagingCellTablePanel;
import com.bradley.gwt.user.client.celltable.TextColumn;
import com.bradley.gwt.user.client.event.EntityProxyRemoteChange;
import com.bradley.gwt.user.client.event.EntityProxyRemoteChange.Handler;
import com.bradley.gwt.user.client.event.EntityProxyRemoteChange.WriteOperation;
import com.bradley.gwt.user.client.resource.AddButtonResources;
import com.bradley.gwt.user.client.resource.ButtonResources;
import com.bradley.gwt.user.client.ui.Button;
import com.bradley.gwt.user.client.ui.CancelButton;
import com.bradley.gwt.user.client.ui.ComboBox;
import com.bradley.gwt.user.client.ui.DateTimeEditor;
import com.bradley.gwt.user.client.ui.Dialog;
import com.bradley.gwt.user.client.ui.MenuButton;
import com.bradley.gwt.user.client.ui.Notifier;
import com.bradley.gwt.user.client.ui.Renderer;
import com.bradley.gwt.user.client.ui.SaveButton;
import com.bradley.gwt.user.client.ui.SuperBoxSelect;
import com.bradley.gwt.user.client.ui.ToolBar;
import com.bradley.gwt.user.client.ui.grid.Grid;
import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.web.bindery.event.shared.Event;

public class TestApp implements EntryPoint {

	@Override
	public void onModuleLoad() {

		demoCellTableDialog();
		//demoNotifications();
		//demoMoveAnimation();
		//demoButtons();
		//demoDualListBox();
		//demoEditorPanel();
		//demoMasking();
		//demoDialog();
		//demoComboBox();
		//demoSuperBoxSelect();
		//demoGrid();
		//demoTooltip();
		//demoToolBar();
		//demoDateEditor();
		//demoRemoteChange();
	}
	
	protected void demoMoveAnimation() {
		Label label = new Label("test");
		label.getElement().getStyle().setPosition(Position.ABSOLUTE);
		RootPanel.get().add(label);
		Animation ani = new MoveAnimation(label.getElement(), 600, 600);
		ani.run(5000);
	}
	
	protected void demoRemoteChange() {
		EventBus eventBus = new SimpleEventBus();
		
		EntityProxyRemoteChange.registerForProxyType(eventBus, EmployeeProxy.class, new Handler<EmployeeProxy>() {

			@Override
			public void onRemoteChange(EntityProxyRemoteChange<EmployeeProxy> event) {
				Window.alert("received");
			}
		});
		EmployeeRequestFactory factory = GWT.create(EmployeeRequestFactory.class);
		EmployeeProxy employee = factory.request().create(EmployeeProxy.class);
		Event event = new EntityProxyRemoteChange<EmployeeProxy>(employee, WriteOperation.SAVE);
		eventBus.fireEventFromSource(event, EmployeeProxy.class);
	}
	
	protected void demoDateEditor() {
		DateTimeEditor dt = new DateTimeEditor();
		RootPanel.get().add(dt);
	}
	
	protected void demoToolBar() {
		ToolBar toolbar = new ToolBar();
		toolbar.add(new Button("test"));
		
		MenuButton btn = new MenuButton("menu button", (ButtonResources) GWT.create(AddButtonResources.class));
		btn.add(new Label("line 1"));
		btn.add(new Label("line 2"));
		toolbar.add(btn);
		RootPanel.get().add(toolbar);
	}
	
	protected void demoTooltip() {
		Label label = new Label("target");
		RootPanel.get().add(label);
		new DemoTooltip(label);
	}
	
	protected void demoGrid() {
		Grid grid = new Grid(100, 3);
		RootPanel.get().add(grid);
	}
	
	protected void demoSuperBoxSelect() {
		final SuperBoxSelect<EmployeeProxy> select = new SuperBoxSelect<EmployeeProxy>();
		select.setRenderer(new Renderer<EmployeeProxy>() {

			@Override
			public String render(EmployeeProxy object) {
				return object.getFirstName() + " " + object.getLastName();
			}
		});
		RootPanel.get().add(select);
		
		EmployeeRequestFactory factory = GWT.create(EmployeeRequestFactory.class);
		EmployeeProxy employee = factory.request().create(EmployeeProxy.class);
		employee.setFirstName("Brad");
		employee.setLastName("Test");
		select.addSelection(employee);
		
		final Set<EmployeeProxy> list = new HashSet<EmployeeProxy>();
		for (int i = 0; i < 5; i++) {
			employee = factory.request().create(EmployeeProxy.class);
			employee.setFirstName("first" + i);
			employee.setLastName("last" + i);
			list.add(employee);
		}
		
		select.getInput().addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				select.setAutoCompleteSelections(list);
			}
		});
	}
	
	protected void demoComboBox() {
		ComboBox<String> combo = new ComboBox<String>();
		combo.addItem("test");
		combo.addItem("test 2");
		RootPanel.get().add(combo);
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
		EmployeeEditor editor = new EmployeeEditor();
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
		CellTable<EmployeeProxy> table = new CellTable<EmployeeProxy>();
		TextColumn<EmployeeProxy> nameColumn = new TextColumn<EmployeeProxy>() {

			@Override
			public String getValue(EmployeeProxy e) {
				return e.getFirstName();
			}
		};
		nameColumn.setSortable(true);
		table.addColumn(nameColumn, "First name");
		
		TextColumn<EmployeeProxy> lastName = new TextColumn<EmployeeProxy>() {

			@Override
			public String getValue(EmployeeProxy object) {
				return object.getLastName();
			}
		};
		lastName.setSortable(true);
		table.addColumn(lastName, "Last Name");
		
		EmployeeRequestFactory factory = GWT.create(EmployeeRequestFactory.class);
		List<EmployeeProxy> employees = new LinkedList<EmployeeProxy>();
		for (int i = 0; i < 50; i++) {
			EmployeeProxy employee = factory.request().create(EmployeeProxy.class);
			employee.setFirstName("Test " + i);
			employee.setLastName("test");
			employees.add(employee);
		}
		
		ListDataProvider<EmployeeProxy> provider = new ListDataProvider<EmployeeProxy>(employees);
		provider.addDataDisplay(table);
		table.setRowCount(50, true);
		
		table.getColumnSortList().push(nameColumn);
		
		table.addColumnSortHandler(new ColumnSortEvent.Handler() {

			@Override
			public void onColumnSort(ColumnSortEvent event) {
				Window.alert("sorting");
			}
			
		});

		PagingCellTablePanel<EmployeeProxy> panel = new PagingCellTablePanel<EmployeeProxy>(table);
		panel.getToolBar().add(new CancelButton());
		Dialog d = new Dialog("Employees");
		d.setWidget(panel);
		d.show();
	}
}
