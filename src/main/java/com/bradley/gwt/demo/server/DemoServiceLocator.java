package com.bradley.gwt.demo.server;

import com.google.gwt.requestfactory.shared.ServiceLocator;

public class DemoServiceLocator implements ServiceLocator {

	@Override
	public Object getInstance(Class<?> clazz) {
		try {
		      return clazz.newInstance();
		    } catch (InstantiationException e) {
		      throw new RuntimeException(e);
		    } catch (IllegalAccessException e) {
		      throw new RuntimeException(e);
		    }
	}

}
