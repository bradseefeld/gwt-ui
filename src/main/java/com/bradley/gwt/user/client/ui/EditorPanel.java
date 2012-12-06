package com.bradley.gwt.user.client.ui;

import com.bradley.gwt.user.client.resource.EditorPanelCssResource;
import com.bradley.gwt.user.client.resource.UIClientBundle;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;

/**
 * The EditorPanel is a convenience form panel that can be used with the Editor
 * framework. Its main goal is to remove boilerplate code that is needed to setup
 * a visually appealing form. This class will provide basic usability features such
 * as:
 * 
 *   - auto focus a form field
 *   - justify labels such that labels are in the left column and fields are in the right
 *   - present input/validation errors (TODO)
 *   - style required fields to draw more attention to them
 *   - provide empty text description of fields in a progressive way
 *   - highlight currently focused field
 * 
 * @param <T> The type being edited
 */
public class EditorPanel<T> extends Composite implements Editor<T> {

	protected static final EditorPanelCssResource css = UIClientBundle.INSTANCE.getEditorCssResource();
	
	public EditorPanel() {
		css.ensureInjected();
		enhance();
	}
		
	public void enhance() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				highlight(getElement());
				
				try {
					focus(getElement());
				} catch (Exception e) {
					// Ignore. Get some focus exceptions from IE from time to time.
				}
			}
		});
	}
	
	/**
	 * Automatically focus the first element in the form.
	 * 
	 * @param el The parent element of the form.
	 */
	public static native void focus(Element el) /*-{
		var inputs = $wnd.$('input', el);
		if (inputs.length > 0) {
			inputs[0].focus();
		}
	}-*/;
	
	/**
	 * Automatically justify labels and fields such that all labels have equal
	 * widths so that it looks like we have two columns.
	 * 
	 * @param el The element of the parent form.
	 */
	public static native void autoWidth(Element el) /*-{
		var max = 0;
		$wnd.$('.label.auto', el).each(function() {
        if ($wnd.$(this).width() > max)
            max = $wnd.$(this).width();    
    	});
    	
    	if (max > 0) {
    		$wnd.$('.label.auto', el).width(max);
    	}
	}-*/;
	
	/**
	 * Automatically apply a focus style to inputs as they are focused. Theoretically,
	 * this is probably better off in the input super class, but since that would
	 * require us to extend every input type offered by GWT, this is much more
	 * practical.
	 * 
	 * @param el
	 */
	public static native void highlight(Element el) /*-{
		$wnd.$('input', el).each(function() {
			var input = $wnd.$(this);
			input.focus(function() {
				input.addClass('focus');
			});
			
			input.blur(function() {
				input.removeClass('focus');
			});
		});
	}-*/;
}
