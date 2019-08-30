package org.jwellman.app.layouts.examples;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BasicWithJLabelDemo extends BasicWithPanels {

	public BasicWithJLabelDemo() {
		super();
		
		this.name = "JLabel Demo";	
	}
	
	protected Component createCenterComponent() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		
		JLabel label = null; // reusable reference
		
		label = a("Left/Top");
		label.setHorizontalAlignment(LEFT);
		label.setVerticalAlignment(TOP);
		panel.add(label);
		
		label = a("Center/Top");
		label.setHorizontalAlignment(CENTER);
		label.setVerticalAlignment(TOP);
		panel.add(label);
		
		label = a("Right/Top");
		label.setHorizontalAlignment(RIGHT);
		label.setVerticalAlignment(TOP);
		panel.add(label);
		
		label = a("Left/Center(Default)");
		panel.add(label);
		
		label = a("Center/Center");
		label.setHorizontalAlignment(CENTER);
		label.setVerticalAlignment(CENTER);
		panel.add(label);
		
		label = a("Right/Center");
		label.setHorizontalAlignment(RIGHT);
		label.setVerticalAlignment(CENTER);
		panel.add(label);
		
		label = a("Left/Bottom");
		label.setHorizontalAlignment(LEFT);
		label.setVerticalAlignment(BOTTOM);
		panel.add(label);
		
		label = a("Center/Bottom");
		label.setHorizontalAlignment(CENTER);
		label.setVerticalAlignment(BOTTOM);
		panel.add(label);
		
		label = a("Right/Bottom");
		label.setHorizontalAlignment(RIGHT);
		label.setVerticalAlignment(BOTTOM);
		panel.add(label);
		
		return panel;
	}
	
	private JLabel a(String text) {
		JLabel label = new JLabel(text);
		label.setBorder(BORDER_LINE);
		return label;
	}
}
