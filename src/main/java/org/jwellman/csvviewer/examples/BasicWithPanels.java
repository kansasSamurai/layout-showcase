package org.jwellman.csvviewer.examples;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BasicWithPanels extends SimpleBorderLayout {

	public BasicWithPanels() {
		super();
		
		this.name = "Simple with Panels";	
	}
	
	protected Component createSouthComponent() {		
		return this.createPanel("South");
	}

	protected Component createNorthComponent() {
		return this.createPanel("North");
	}

	protected Component createWesternComponent() {
		return this.createPanel("West");
	}

	protected Component createEasternComponent() {
		return this.createPanel("East");
	}

	protected Component createCenterComponent() {
		return this.createPanel("Center");
	}

	private Component createPanel(String string) {
		boolean showBorder = true;
		boolean addSecondButton = false;
		boolean useDefaultLayout = false;
		
		JPanel p = new JPanel();
		if (showBorder)
			p.setBorder(BorderFactory.createLineBorder(Color.red, 1));
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
