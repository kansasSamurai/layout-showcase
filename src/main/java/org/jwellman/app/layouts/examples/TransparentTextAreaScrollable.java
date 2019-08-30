package org.jwellman.app.layouts.examples;

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
 * A transparent JTextArea set in a JScrollPane within
 * a JPanel that has been customized with an image background.
 * 
 * @author rwellman
 *
 */
@SuppressWarnings("serial")
public class TransparentTextAreaScrollable extends ExampleAdapter implements ActionListener {

	private JTextArea text;
	private JScrollPane scroll;
	private JToggleButton btn;
	private String BTN_LABEL_READONLY = "Read Only";
	private String BTN_LABEL_EDITABLE = "Editable";
	
	public TransparentTextAreaScrollable() {
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
		text.setLineWrap(false); // true works; testing false
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
		
		btn = new JToggleButton(BTN_LABEL_READONLY);
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
		btn.setText(enabled ? BTN_LABEL_EDITABLE : BTN_LABEL_READONLY);
	}
	
}
