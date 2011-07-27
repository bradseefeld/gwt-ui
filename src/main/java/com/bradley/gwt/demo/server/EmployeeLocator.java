package com.bradley.gwt.demo.server;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.bradley.gwt.demo.server.domain.Employee;
import com.google.web.bindery.requestfactory.shared.Locator;

public class EmployeeLocator extends Locator<Employee, String> {

	public List<Employee> paginate(int offset, int limit, String sortField,
			boolean isAscending) {
		List<Employee> list = new LinkedList<Employee>();

		for (int i = 0; i < 100; i++) {
			Employee e = new Employee();
			e.setFirstName("brad " + i);
			e.setLastName("last " + i);
			e.setId(i + "");
			list.add(e);
		}

		return list;
	}

	public Integer count() {
		return 2312;
	}

	@Override
	public Employee create(Class<? extends Employee> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Employee find(Class<? extends Employee> clazz, String id) {
		Employee e = new Employee();
		e.setFirstName("existing");
		return e;
	}

	@Override
	public Class<Employee> getDomainType() {
		return Employee.class;
	}

	@Override
	public String getId(Employee e) {
		return e.getId();
	}

	@Override
	public Class<String> getIdType() {
		return String.class;
	}

	@Override
	public Object getVersion(Employee e) {
		Date d = e.getDateUpdated();
		if (d == null) {
			return 0;
		}

		return d.getTime();
	}

}
