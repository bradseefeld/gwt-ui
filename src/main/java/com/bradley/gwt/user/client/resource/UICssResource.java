package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.CssResource;

public interface UICssResource extends CssResource {
	
	@ClassName("hidden")
	String hidden();
	
	@ClassName("notifications")
	String notifications();
	
	@ClassName("success")
	String success();
	
	@ClassName("error")
	String error();
	
	@ClassName("warn")
	String warn();
	
	@ClassName("info")
	String info();
	
	@ClassName("notification")
	String notification();
	
	@ClassName("no-data")
	String noData();
}
