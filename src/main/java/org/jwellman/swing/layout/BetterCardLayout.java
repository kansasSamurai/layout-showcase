package org.jwellman.swing.layout;

import java.awt.CardLayout;
import java.awt.Container;

/**
 * Improve the awkward API of CardLayout;
 * particularly by storing the parent container so
 * that it does not need to be a method argument
 * every time we want to do something.
 * 
 * @author rwellman
 *
 */
public class BetterCardLayout extends CardLayout {

	private static final long serialVersionUID = 1L;
	
	private Container parent;

	public void show(String name) {
		super.show(parent, name);
	}
	
	public void next() {
		super.next(parent);
	}
	
	public void last() {
		super.last(parent);
	}
	
	public Container getParent() {
		return parent;
	}

	public void setParent(Container parent) {
		this.parent = parent;
		parent.setLayout(this);
	}
	
}
