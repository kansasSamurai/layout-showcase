package org.jwellman.swing.jlabel;

import java.awt.Dimension;

import javax.swing.JLabel;

/**
 * A JLabel that will not grow to fill its space
 * (assuming that the layout manager respects the max/preferred sizes)
 * 
 * @author rwellman
 */
public class FixedLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public FixedLabel(String string) {
		super(string);
	}

	@Override
	public Dimension getMaximumSize() {
		return this.getPreferredSize();
	}
	
}
