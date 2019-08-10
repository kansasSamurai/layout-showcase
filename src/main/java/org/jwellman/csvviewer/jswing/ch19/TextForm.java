package org.jwellman.csvviewer.jswing.ch19;

import javax.swing.*;

import org.jwellman.csvviewer.examples.ExampleAdapter;

import java.awt.event.*;
import java.awt.*;

/**
 * A simple label/field form panel.
 * Modified as necessary to fit within the example framework.
 * 
 * Java Swing,  
 * Chapter 19
 *
 * @author rwellman
 *
 */
@SuppressWarnings("serial")
public class TextForm extends ExampleAdapter {

	private JTextField[] fields;

	/**
	 * Create the same form as when using main() method.
	 */
	public TextForm() {
		this(DATA.labels, DATA.mnemonics, DATA.widths, DATA.descs);
		
		this.name = "Ch. 19, TextForm";
		this.textDescription = "Long description goes here...";
		this.setImagePath("/images/examples/swing.gif");
	}
	
	/**
	 * Create a form with the specified labels, tooltips, and sizes.
	 *  
	 * @param labels
	 * @param mnemonics
	 * @param widths
	 * @param tips
	 */
	public TextForm(
	    String[] labels, char[] mnemonics, int[] widths, String[] tips) {
		
		super(new BorderLayout());
		
		JPanel labelPanel = new JPanel(new GridLayout(labels.length, 1));
		add(labelPanel, BorderLayout.WEST);
		
		JPanel fieldPanel = new JPanel(new GridLayout(labels.length, 1));
		add(fieldPanel, BorderLayout.EAST); // CENTER // modified
		
		fields = new JTextField[labels.length];
		for (int i = 0; i < labels.length; i += 1) {
			fields[i] = new JTextField();
			if (i < tips.length)
				fields[i].setToolTipText(tips[i]);
			if (i < widths.length)
				fields[i].setColumns(widths[i]);

			JLabel lab = new JLabel(labels[i], JLabel.RIGHT);
			lab.setLabelFor(fields[i]);
			if (i < mnemonics.length)
				lab.setDisplayedMnemonic(mnemonics[i]);

			labelPanel.add(lab);
			JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
			p.add(fields[i]);
			fieldPanel.add(p);
		}
	}

	public String getText(int i) {
		return (fields[i].getText());
	}

	public static void main(String[] args) {
		String[] labels = { "First Name", "Middle Initial", "Last Name", "Age" };
		char[] mnemonics = { 'F', 'M', 'L', 'A' };
		int[] widths = { 15, 1, 15, 3 };
		String[] descs = { "First Name", "Middle Initial", "Last Name", "Age" };

		final TextForm form = new TextForm(labels, mnemonics, widths, descs);

		JButton submit = new JButton("Submit Form");

		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(form.getText(0) + " " + form.getText(1)
						+ ". " + form.getText(2) + ", age " + form.getText(3));
			}
		});

		JPanel p = new JPanel();
		p.add(submit);
		
		JFrame f = new JFrame("Text Form Example");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.getContentPane().add(form, BorderLayout.NORTH);
		f.getContentPane().add(p, BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}
	
	private static class DATA {
		static final String[] labels = { "First Name", "Middle Initial", "Last Name", "Age" };
		static final char[] mnemonics = { 'F', 'M', 'L', 'A' };
		static final int[] widths = { 15, 1, 15, 3 };
		static final String[] descs = { "First Name", "Middle Initial", "Last Name", "Age" };
	}
	
}
