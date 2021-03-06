package org.jwellman.app.layouts.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import org.jwellman.swing.jpanel.ImagePanel;

/**
 * A transparent JTextArea within a JPanel that has been 
 * customized with an image background.
 * 
 * Note:  This version purposely omits putting the JTextArea
 * in a JScrollPane.  I wanted to prove the concept was possible
 * before introducing other components. 
 *  
 * See TransparentTextAreaScrollable for making it scrollable
 * (which you will probably always want)
 * 
 * @author rwellman
 *
 */
@SuppressWarnings("serial")
public class TransparentTextArea extends ExampleAdapter implements ActionListener {

	JTextArea text;
	JToggleButton btn;
	
	public TransparentTextArea() {
		super();
		this.setLayout(new BorderLayout());
		// this.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
		this.createContents();
		
		this.name = "Transparent JTextArea";
		this.setImagePath("/images/examples/swing.gif");
	}
	
	/**
	 * Utility method to support the constructor
	 */
	private void createContents() {
		final Color textColor = Color.white;
		
		text = new JTextArea("Start with this text...");
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		text.setOpaque(false);
		text.setBackground(TRANSPARENT);
		text.setForeground(textColor);
		text.setCaretColor(textColor);
		text.setBorder(BorderFactory.createEmptyBorder(9,9,9,9));
		
		JPanel panel = new ImagePanel("/images/golf/silhouette_approachshot.jfif");
		panel.setLayout(new BorderLayout());
		panel.add(text, BorderLayout.CENTER);						
		this.add(panel, BorderLayout.CENTER);
		
		btn = new JToggleButton("Read Only");
		btn.addActionListener(this);
		panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(9,0,9,0));
		panel.add(btn);
		this.add(panel, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final boolean enabled = btn.getModel().isSelected();
		text.setEditable(enabled);		
		btn.setText(enabled ? "Editable" : "Read Only");
	}
	
}
