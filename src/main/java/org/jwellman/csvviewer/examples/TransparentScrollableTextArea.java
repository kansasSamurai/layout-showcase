package org.jwellman.csvviewer.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import org.jwellman.swing.jpanel.ImagePanel;

/**
 * 
 * @author rwellman
 *
 */
@SuppressWarnings("serial")
public class TransparentScrollableTextArea extends ExampleAdapter implements ActionListener {

	private JTextArea text;
	private JToggleButton btn;
	private JScrollPane scroll;
	
	private static final Color TRANSPARENT = new Color(0,0,0,0);
	
	public TransparentScrollableTextArea() {
		super();
		this.setLayout(new BorderLayout());
		// this.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
		this.createContents();
		
		this.name = "Trspt Scrollable JTextArea";
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
		
		scroll = new JScrollPane(text);
		scroll.setBorder(null);
		scroll.setOpaque(false); // scroll.setBackground(TRANSPARENT);
		scroll.getViewport().setOpaque(false); // scroll.getViewport().setBackground(TRANSPARENT);
		
		JPanel panel = new ImagePanel("/images/golf/golf_ballongreen.jpg");
		panel.setLayout(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);						
		this.add(panel, BorderLayout.CENTER);
		
		btn = new JToggleButton("Read Only");
		btn.addActionListener(this);
		btn.setFocusable(false);
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
