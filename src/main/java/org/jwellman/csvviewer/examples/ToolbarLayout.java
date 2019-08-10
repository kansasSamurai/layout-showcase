package org.jwellman.csvviewer.examples;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.jwellman.csvviewer.buttons.AFlatButton;
import org.jwellman.csvviewer.buttons.FlatButtonBasic;

public class ToolbarLayout extends SimpleBorderLayout {

	private static final long serialVersionUID = 1L;

	private static final Color DARK = Color.DARK_GRAY;
	
	private static final Color MEDIUM = Color.GRAY;
	
	public ToolbarLayout() {
		super();
		
		this.name = "Toolbar Layout";
	}
	
	protected Component createSouthComponent() {		
		JPanel panel = (JPanel) this.createPanel("South", true);
		panel.setBackground(DARK);
		return panel;
	}

	protected Component createNorthComponent() {
		JPanel panel = (JPanel) this.createPanel("North", true);
		panel.setBackground(DARK);
		return panel;
	}

	protected Component createWesternComponent() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.setBackground(MEDIUM);
		
		JButton button = this.createButton(AFlatButton.PROFILE);
		p.add(button);
		
		button = this.createButton(AFlatButton.LIBRARY);
		p.add(button);
		
		p.add(Box.createVerticalGlue());
		
		button = this.createButton(AFlatButton.BUGREPORT);
		p.add(button);
		
		return p;
	}

	protected Component createEasternComponent() {
		return this.createPanel("East", false);
	}

	protected Component createCenterComponent() {
		return this.createPanel("Center", false);
	}

	private JButton createButton(String action) {
		JButton button = null;
		
		int version = 1;
		switch (version) {
		case 1:
			button = new FlatButtonBasic(action);
			break;
		case 2:
			break;
		}
		
		return button;
	}
	
	private Component createPanel(String string, boolean showBorder) {
		boolean addSecondButton = false;
		boolean useDefaultLayout = false;
		
		JPanel p = new JPanel();
		if (showBorder)
			p.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
			// p.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		if (!useDefaultLayout)
			p.setLayout(new GridBagLayout());
		
		JButton button = new JButton(string);
		p.add(button);
		
		if (addSecondButton) {
			button = new JButton(string);
			p.add(button);
		}

		return p;
	}

}
