package com.bradley.gwt.demo.user.client.entity;

import com.bradley.gwt.demo.server.EmployeeLocator;
import com.bradley.gwt.demo.server.domain.Employee;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(value=Employee.class, locator=EmployeeLocator.class)
public interface EmployeeProxy extends EntityProxy {
	String getFirstName();
	String getLastName();
	void setFirstName(String name);
	void setLastName(String name);
}
