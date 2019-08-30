package org.jwellman.swing.event;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

/**
 * A Focus Adapter that leaves textfield selection visible when a textfield loses focus.
 * Inspired by: https://www.javaworld.com/article/2076720/focus-on-swing.html
 * 
 * Personally, I think this should be the default behavior in the JDK
 * but, they either disagree, or they won't change it because it is
 * long-standing behavior and they have a pretty strong aversion to
 * changing historical behavior. 
 * (which, unfortunately for this, is probably a good thing)
 * 
 * @author rwellman
 *
 */
public class FocusAdapterPersistentSelection extends FocusAdapter {
	
	private JTextField textfield;
	
	public FocusAdapterPersistentSelection(JTextField f) {
		this.textfield = f;
	}
	
    public void focusGained(FocusEvent e) {
    }
    
    public void focusLost(FocusEvent e) {
    	this.textfield.getCaret().setSelectionVisible(true);
    }

}
