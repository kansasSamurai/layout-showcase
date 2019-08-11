package org.jwellman.app.layouts.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BasicWithFlowLayout extends BasicWithPanels {

	public BasicWithFlowLayout() {
		super();
		
		this.name = "FlowLayout in Center";	
	}
	
	protected Component createCenterComponent() {
		return version2();
	}
	
	private Component version1() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(p(8, Color.RED), BorderLayout.NORTH);
  		panel.add(p(8, Color.RED), BorderLayout.SOUTH);
// East and West override center so this destroys the desired effect
// (because buttons in north,south,east,west do not wrap)
// 		panel.add(p(8, Color.BLACK), BorderLayout.EAST);
//		panel.add(p(8, Color.BLACK), BorderLayout.WEST);
  		
// When adding the scrollpane, it gives the buttons extra
// horizontal space so we lose the wrapping effect:
//  		JScrollPane pane = new JScrollPane();
//  		pane.getViewport().add(p(20, Color.BLUE));
		panel.add(p(20, Color.BLUE), BorderLayout.CENTER);
		
		return panel;
	}

	private Component version2() {
		JPanel panel = new JPanel(new BorderLayout());
		
//		JPanel p = b(); p.add(p(8, Color.RED), BorderLayout.CENTER);
//		panel.add(p, BorderLayout.NORTH);
		panel.add(p(8, Color.RED), BorderLayout.NORTH);
//  		panel.add(p(8, Color.RED), BorderLayout.SOUTH);
// East and West override center so this destroys the desired effect
// (because buttons in north,south,east,west do not wrap)
// 		panel.add(p(8, Color.BLACK), BorderLayout.EAST);
// 		panel.add(p(8, Color.BLACK), BorderLayout.WEST);
  		
// When adding the scrollpane, it gives the buttons extra
// horizontal space so we lose the wrapping effect:
//  		JScrollPane pane = new JScrollPane();
//  		pane.getViewport().add(p(20, Color.BLUE));
		
		JPanel p1 = b(); p1.add(p(20, Color.BLUE), BorderLayout.CENTER);
		JPanel p2 = p(20, Color.BLUE); p2.add(p1);
		panel.add(p2, BorderLayout.CENTER);

//		panel.add(p(20, Color.BLUE), BorderLayout.CENTER);
		
		return panel;
	}

	private JPanel b() {
		JPanel p = new JPanel(new BorderLayout());
		return p;
	}
	
	private JPanel p(int number, Color f) {
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JButton button;
		for (int i = 1; i <= number; i++) {
			button = new JButton("Button " + (i<10 ? "0" : "") + i);
			button.setForeground(f);
			p.add(button);			
		}

		return p;
	}

}
