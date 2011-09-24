package com.bradley.gwt.user.client.animation;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

public class MoveAnimation extends Animation {
	
	protected Element el;
	
	protected int destinationX;
	
	protected int destinationY;
	
	protected int originX;
	
	protected int originY;
	
	public MoveAnimation(Element el, int x, int y) {
		this.el = el;
		this.destinationX = x;
		this.destinationY = y;
		
		this.originX = el.getOffsetLeft();
		this.originY = el.getOffsetTop();
	}
	
	public MoveAnimation(Widget widget, int x, int y) {
		this(widget.getElement(), x, y);
	}

	@Override
	protected void onUpdate(double progress) {
		int xDistance = destinationX - originX;
		int yDistance = destinationY - originY;
		
		el.getStyle().setLeft(originX + xDistance * progress, Unit.PX);
		el.getStyle().setTop(originY + yDistance * progress, Unit.PX);
	}
	
	@Override
	protected void onComplete() {
		el.getStyle().setLeft(destinationX, Unit.PX);
		el.getStyle().setTop(destinationY, Unit.PX);
	}
}
