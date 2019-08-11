package org.jwellman.swing.jlabel;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;

/**
 * 
 * @author rwellman
 */
public class ActionLabel extends JLabel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);
	
	private AbstractAction action;
	
	public ActionLabel(AbstractAction a) {
		this((String) a.getValue(Action.NAME));
		
		this.addActionListener(a);
	}
	
	public ActionLabel(String text) {
		super(text);
		
		this.setCursor(HAND);
		this.addMouseListener(this);
	}

	/**
	 * 
	 * @param l
	 */
    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    public void removeActionListener(ActionListener l) {
        if ((l != null) && (getAction() == l)) {
            setAction(null);
        } else {
            listenerList.remove(ActionListener.class, l);
        }
    }
    
	@Override
	public void mouseClicked(MouseEvent e) {
		final ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_FIRST, this.getText());

		 // Guaranteed to return a non-null array
	     final Object[] listeners = listenerList.getListenerList();
	     // Process the listeners last to first, notifying
	     // those that are interested in this event
	     for (int i = listeners.length-2; i >= 0; i -= 2) {
	         if (listeners[i] == ActionListener.class) {
	             ((ActionListener)listeners[i+1]).actionPerformed(event);
	         }
	     }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public AbstractAction getAction() {
		return action;
	}

	public void setAction(AbstractAction action) {
		this.action = action;
	}
		
}
