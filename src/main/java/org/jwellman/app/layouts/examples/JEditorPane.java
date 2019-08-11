package org.jwellman.app.layouts.examples;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;

import org.jwellman.swing.text.BetterHTMLEditorKit;

@SuppressWarnings("serial")
public class JEditorPane extends ExampleAdapter implements ActionListener {

	private JTextArea text;
	private javax.swing.JEditorPane editor;
	private JButton btn;
	private JScrollPane scrollText;
	private JScrollPane scrollEditor;
	private JSplitPane splitter;
	
	private static final String COMMAND_CLEAR  = "Clear";
    private static final String COMMAND_DEBUG  = "Debug";
	private static final String COMMAND_RENDER = "Render HTML";
	
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
		editor.setEditable(false);		
		scrollEditor = new JScrollPane(editor); 
		scrollEditor.setBorder(null);
		splitter.setTopComponent(scrollEditor);
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(9,0,9,0));

		btn = new JButton(COMMAND_RENDER);
		btn.addActionListener(this);
		btn.setFocusPainted(false);
		panel.add(btn);

		btn = new JButton(COMMAND_CLEAR);
		btn.addActionListener(this);
		btn.setFocusPainted(false);
		panel.add(btn);
		
		btn = new JButton(COMMAND_DEBUG);
		btn.addActionListener(this);
		btn.setFocusPainted(false);
		panel.add(btn);
		
		this.add(panel, BorderLayout.SOUTH);
		
		boolean useCustomEditorKit = true;
		if (useCustomEditorKit) {
			editor.setEditorKit(new BetterHTMLEditorKit());
//			HTMLEditorKit hkit = (HTMLEditorKit)kit;
//			hkit.getViewFactory();			
		} else {
			editor.setContentType("text/html");			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Document doc;
		
		switch (e.getActionCommand()) {
		case COMMAND_CLEAR:
			doc = editor.getEditorKit().createDefaultDocument();
			editor.setDocument(doc);
			break;
		case COMMAND_DEBUG:
			doc = editor.getDocument();
			if (doc instanceof HTMLDocument) {
				HTMLDocument html = (HTMLDocument) doc;
				for (HTMLDocument.Iterator it = html.getIterator(HTML.Tag.SPAN);
						it.isValid();
						it.next()				
				) {
					AttributeSet atts = it.getAttributes();					
					for (Enumeration<?> names = atts.getAttributeNames(); names.hasMoreElements();) {
						final Object key = names.nextElement();
						System.out.print(key.toString());
						System.out.println(atts.getAttribute(key));
					}
				}
			}			
			break;
		case COMMAND_RENDER:
			splitter.setDividerLocation(0.75f);
			boolean java8 = false;
			if (java8) {
				// TODO fix then when using a java8 compliant IDE / Eclipse Kepler SR2+
				// SwingUtilities.invokeLater(run -> {editor.setText(text.getText());} );
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
		
}
