package org.jwellman.app.layouts.examples;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jwellman.app.dialogs.example01.Subscribe;

/**
 * 
 * @author rwellman
 *
 */
@SuppressWarnings("serial")
public class BasicJDialogDemo extends BasicWithPanels {

	public BasicJDialogDemo() {
		super();
		
		this.name = "JDialog Demo";	
	}
	
	protected Component createCenterComponent() {
		final JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		
		JButton button = null;
		
		button = new JButton("Subscribe");
		button.addActionListener( ev -> {
			JDialog d = new Subscribe();
			d.setLocationRelativeTo(null);
			d.setVisible(true);
		} );
		panel.add(button);
		

		
		return panel;
	}

}
