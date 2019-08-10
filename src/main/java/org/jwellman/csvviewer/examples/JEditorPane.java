package org.jwellman.csvviewer.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;

@SuppressWarnings("serial")
public class JEditorPane extends ExampleAdapter 
	implements ActionListener, HyperlinkListener {

	private JTextArea text;
	private javax.swing.JEditorPane editor;
	private JTextPane textpane;
	private JButton btn;
	private JScrollPane scrollText;
	private JScrollPane scrollEditor;
	private JSplitPane splitter;
	
	private static final Color TRANSPARENT = new Color(0,0,0,0);
	private static final String COMMAND_RENDER = "Render HTML";
	private static final String COMMAND_CLEAR  = "Clear";
	
	public JEditorPane() {
		super();
		this.setLayout(new BorderLayout());
		// this.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
		this.createContents();
		
		this.name = "JEditorPane Example";
		this.setImagePath("/images/examples/swing.gif");
	}

	private void createContents() {
		splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitter.setOneTouchExpandable(true);
		this.add(splitter, BorderLayout.CENTER);
				
		text = new JTextArea("Put HTML content here...");
		scrollText = new JScrollPane(text); scrollText.setBorder(null);
		splitter.setBottomComponent(scrollText);
		
		editor = new javax.swing.JEditorPane();
		scrollEditor = new JScrollPane(editor); scrollEditor.setBorder(null);
		splitter.setTopComponent(scrollEditor);
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(9,0,9,0));

		btn = new JButton(COMMAND_RENDER);
		btn.addActionListener(this);
		btn.setFocusable(false);		
		panel.add(btn);

		btn = new JButton(COMMAND_CLEAR);
		btn.addActionListener(this);
		btn.setFocusable(false);		
		panel.add(btn);

		this.add(panel, BorderLayout.SOUTH);
		
		editor.setEditable(false);
		editor.setContentType("text/html");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
		case COMMAND_CLEAR:
			Document doc = editor.getEditorKit().createDefaultDocument();
			editor.setDocument(doc);
			break;
		case COMMAND_RENDER:
			splitter.setDividerLocation(0.75f);
			boolean java8 = true;
			if (java8) {
				SwingUtilities.invokeLater(() -> {
				    editor.setText(text.getText());
				    editor.setCaretPosition(0);
				} );
			} else {
		        SwingUtilities.invokeLater(
	                new Runnable() { @Override public void run() {
	        			editor.setText(text.getText());		
					    editor.setCaretPosition(0);
	                }
		        } );				
			}
			break;
		}
		
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {
		System.out.println("URL: " + e.getURL() );
		System.out.println("DSC: " + e.getDescription() );
	}
		
}
