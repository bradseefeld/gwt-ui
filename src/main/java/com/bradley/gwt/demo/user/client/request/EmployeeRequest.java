package com.bradley.gwt.demo.user.client.request;

import java.util.List;

import com.bradley.gwt.demo.server.DemoServiceLocator;
import com.bradley.gwt.demo.server.EmployeeLocator;
import com.bradley.gwt.demo.user.client.entity.EmployeeProxy;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(value=EmployeeLocator.class, locator=DemoServiceLocator.class)
public interface EmployeeRequest extends RequestContext {

	/**
	 * 
	 * 
	 * @param offset
	 * @param limit
	 * @param sortField
	 * @param isAscending
	 * @return
	 */
	Request<List<EmployeeProxy>> paginate(int offset, int limit, String sortField, boolean isAscending);
	
	/**
	 * Count the number of items for pagination.
	 * 
	 * @return
	 */
	Request<Integer> count();
	
	Request<EmployeeProxy> save(EmployeeProxy employee);
}
