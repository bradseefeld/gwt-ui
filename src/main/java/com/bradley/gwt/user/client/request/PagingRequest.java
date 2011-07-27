package com.bradley.gwt.user.client.request;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.Request;

public interface PagingRequest<T> {

	/**
	 * 
	 * 
	 * @param offset
	 * @param limit
	 * @param sortField
	 * @param isAscending
	 * @return
	 */
	Request<List<T>> paginate(int offset, int limit, String sortField, boolean isAscending);
	
	/**
	 * Count the number of items for pagination.
	 * 
	 * @return
	 */
	Request<Integer> count();
}
