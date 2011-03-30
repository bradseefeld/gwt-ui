package com.bradley.gwt.user.client.ui;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class ScrollableDialogBox extends DialogBox {
	
	protected HorizontalPanel captionPanel = new HorizontalPanel();
	protected Button closer = new Button("X");
	protected SimplePanel contentPanel = new SimplePanel();
	protected int Width, Height;
	protected CaptionTitle captionTitle;
	
	protected static final UICssResource css = UIClientBundle.INSTANCE.getUICssResource();
	
	private static final Logger logger = Logger.getLogger(ScrollableDialogBox.class.getName());
	
	static {
		css.ensureInjected();
	}
	
	public ScrollableDialogBox() {
		this(false);
	}

	public ScrollableDialogBox(boolean autoHide) {
		this(autoHide, false);
	}

	public ScrollableDialogBox(boolean autoHide, boolean modal) {
		super(autoHide, modal);
		
		addStyleName(css.scrolledDialog());
		
		Element td01 = getCellElement(0, 1);
		Widget caption = (Widget) getCaption();
		DOM.removeChild(td01, caption.getElement());
		DOM.appendChild(td01, captionPanel.getElement());
		adopt(this.captionPanel);

		captionTitle = new CaptionTitle();
		captionPanel.add(captionTitle);
		captionPanel.add(closer);
		super.setWidget(contentPanel);
		
		
		// TODO: Refactor this!
		//setWidthPx(200);

		CloserEventHandlers.add(new CloserHandler());

		captionPanel.setStyleName("caption");
		closer.setStyleName("closer");
		closer.setStyleName("title");
		
		// TODO why?
		DOM.setStyleAttribute(closer.getElement(), "color", "black");
	}
	
	public void setText(String text) {
		setCaptionText(text);
	}

	public void setHeightPx(int hgt) {
		contentPanel.setWidth(hgt + "px");
		Height = hgt;
	}

	public void setWidthPx(int wid) {
		contentPanel.setWidth(wid + "px");
		initCaptionWidths(wid);
	}

	public void setSizePx(int wid, int hgt) {
		contentPanel.setSize(wid + "px", hgt + "px");
		Height = hgt;
		initCaptionWidths(wid);
	}

	@Override
	public void setWidget(Widget widget) {
		contentPanel.setWidget(widget);
	}

	@Override
	public Widget getWidget() {
		return contentPanel.getWidget();
	}

	private void initCaptionWidths(int wid) {
		Width = wid;
		captionPanel.setWidth(this.Width + "px");
	}

	public final Caption getCaptionTitle() {
		return captionTitle;
	}

	public String getCaptionHTML() {
		return captionTitle.getHTML();
	}

	public String getCaptionText() {
		return captionTitle.getText();
	}

	public void setCaptionHTML(String html) {
		captionTitle.setHTML(html);
	}

	public void setCaptionText(String text) {
		captionTitle.setText(text);
	}

	protected boolean isCaptionControlEvent(NativeEvent event) {
		return isWidgetEvent(event, captionPanel.getWidget(1));
	}

	static protected boolean isWidgetEvent(NativeEvent event, Widget w) {
		EventTarget target = event.getEventTarget();
		if (Element.is(target)) {
			return w.getElement().isOrHasChild(Element.as(target));
		}
		return false;
	}

	@Override
	public void onBrowserEvent(Event event) {
		if (isCaptionControlEvent(event)) {
			for (CloserEventHandler handler : CloserEventHandlers) {
				switch (event.getTypeInt()) {
				case Event.ONMOUSEUP:
				case Event.ONCLICK:
					handler.onClick(event);
					break;
				case Event.ONMOUSEOVER:
					handler.onMouseOver(event);
					break;
				case Event.ONMOUSEOUT:
					handler.onMouseOut(event);
					break;
				}
			}
			
			return;
		}
		super.onBrowserEvent(event);
	}

	// The events are not firing normally because DialogBox has over-ridden
	// onBrowserEvent with some obtuse logic.
	// So we have to create our own tiny system of event handling for the closer
	// button
	// in conjunction with List<AntiObtusedCloserHandler>
	public interface CloserEventHandler {
		public void onClick(Event event);

		public void onMouseOver(Event event);

		public void onMouseOut(Event event);
	}

	final public List<CloserEventHandler> CloserEventHandlers = new LinkedList<CloserEventHandler>();

	private class CloserHandler implements CloserEventHandler {

		public void onClick(Event event) {
			hide();
			DOM.setStyleAttribute(closer.getElement(), "color", "black");
		}

		public void onMouseOver(Event event) {
			DOM.setStyleAttribute(closer.getElement(), "color", "red");
		}

		public void onMouseOut(Event event) {
			DOM.setStyleAttribute(closer.getElement(), "color", "black");
		}
	}

	protected class CaptionTitle extends HTML implements Caption {
	}

	public Panel getContentPanel() {
		return contentPanel;
	}
}