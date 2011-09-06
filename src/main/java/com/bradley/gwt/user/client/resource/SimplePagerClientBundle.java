package com.bradley.gwt.user.client.resource;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.SimplePager.Resources;

public interface SimplePagerClientBundle extends Resources {
	
	@Override
	@Source("image/arrow.png")
	ImageResource simplePagerNextPage();
	
	@Override
	@Source("image/arrow-disabled.png")
	ImageResource simplePagerNextPageDisabled();
	
	@Override
	@Source("image/arrow-180.png")
	ImageResource simplePagerPreviousPage();
	
	@Override
	@Source("image/arrow-180-disabled.png")
	ImageResource simplePagerPreviousPageDisabled();
	
	@Override
	@Source("image/arrow-stop.png")
	ImageResource simplePagerLastPage();
	
	@Override
	@Source("image/arrow-stop-disabled.png")
	ImageResource simplePagerLastPageDisabled();
	
	@Override
	@Source("image/arrow-stop-180.png")
	ImageResource simplePagerFirstPage();
	
	@Override
	@Source("image/arrow-stop-180-disabled.png")
	ImageResource simplePagerFirstPageDisabled();
}
