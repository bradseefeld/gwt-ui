package com.bradley.gwt.user.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.HasOpenHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Extends the standard GWT DialogBox by adding an OpenHandler and a
 * close icon with support for other controls.
 */
public class ClosableDialogBox extends DialogBox {
	
	protected Label close;
	
	protected FlowPanel contentPanel;
	
    
    public ClosableDialogBox() {
    	this(false, false);
    }
    
    public ClosableDialogBox(boolean autoHide, boolean modal) {
        this(autoHide, modal, true);
    }
    
    public ClosableDialogBox(boolean autoHide, boolean modal, boolean showCloseIcon) {
        super(autoHide, modal);

       close = new Label("X");
       
       contentPanel = new FlowPanel();
       contentPanel.add(close);
       
    }
    
    public void setWidget(Widget widget) {
    	
    	// TODO: Remove other components first!
    	
    	contentPanel.add(widget);
    	super.setWidget(contentPanel);
    }

    public void setCloseIconVisible(boolean visible) {
    	Window.alert("i will close!");
    }

    /**
     * Called when the close icon is clicked. The default
     * implementation hides dialog box.
     */
    protected void onCloseClick(ClickEvent event) {
        hide();
    }
}
