package org.jwellman.app.dialogs.example01;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import org.jwellman.swing.jpanel.RestrictedHeightPanel;

/**
 * 
 * @author rwellman
 *
 */
public class Subscribe extends JDialog {

	private Icon radioselected;
	private Icon radiounselected;
	
	public Subscribe() {
		super();
		this.setModal(true);
		this.setUndecorated(true);
		
		this.build();
		this.pack();
	}
	
	private void build() {
		final Container contentpane = this.getContentPane();
		
		this.getContentPane().setLayout(new BorderLayout());

		JPanel panel = buildPanel(null);		
		JLabel label = new JLabel();
		label.setIcon( this.getIcon("feet.png") );
		panel.add(label);
		contentpane.add(panel, BorderLayout.WEST);

		JPanel center = buildPanel(new BorderLayout());
		
		// Life Icon ( top right )
		label = new JLabel();
		label.setIcon( this.getIcon("lifeicon_big.png") );
		panel = buildPanel(null);		
		panel.add(label);
		center.add(panel, BorderLayout.NORTH);
		
		// Content ( center right )
		center.add(this.buildContent(), BorderLayout.CENTER);
		
		// Subscribe Button ( bottom right )
		JButton button = new JButton("Subscribe");
		button.addActionListener(ev -> {
			this.setVisible(false);
			this.dispose();
		});
		panel = buildPanel(null);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 40, 0));
		panel.add(button);
		center.add(panel, BorderLayout.SOUTH);
		
		contentpane.add(center, BorderLayout.CENTER);		
	}

	private JPanel buildContent() {
		JPanel panel = buildPanel(null);
		BoxLayout layout = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
		panel.setLayout(layout);
		        
		JTextPane text = new JTextPane();
        addTextToDocument(text);
		panel.add(text);

		// Adding glue does not help the sizing issues:
		//panel.add(Box.createVerticalGlue());		

		JTextField field = new JTextField("EMAIL-ADDRESS");
		field.setForeground(Color.LIGHT_GRAY);
		field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));
		field.setHorizontalAlignment(JTextField.CENTER);
		panel.add(wrap(field));
		
		panel.add(Box.createVerticalStrut(20));
		
		JPanel temp = buildPanel(null);
		ButtonGroup g = new ButtonGroup();
		JRadioButton b = radio("MAN'S", g);
		temp.add(b);
		b = radio("WOMAN'S", g);
		temp.add(b);
		panel.add(temp);		
		
		panel.add(Box.createVerticalStrut(20));
		
		return panel;
	}
	
	private Component wrap(Component field) {
		JPanel wrapper = new RestrictedHeightPanel();
		wrapper.setBackground(Color.WHITE);

		BoxLayout layout = new BoxLayout(wrapper, BoxLayout.LINE_AXIS);
		wrapper.setLayout(layout);
		
		wrapper.add(field);			
		return wrapper;
	}

	private JRadioButton radio(String text, ButtonGroup g) {
		JRadioButton b = new JRadioButton(text);
		b.setForeground(Color.LIGHT_GRAY);
		b.setBackground(Color.WHITE);
		b.setSelectedIcon(this.getRadioselected());
		b.setIcon(this.getRadiounselected());
		g.add(b);
		
		return b;
	}
	
	private JPanel buildPanel(LayoutManager m) {
		JPanel p = new JPanel(m == null ? new FlowLayout() : m);
		p.setBackground(Color.WHITE);
		
		return p;
	}
	
	public Icon getIcon(String filename) {		
		URL url = getClass().getResource(filename);

		Icon image = new ImageIcon(url);
        return image;
	}

	private void addTextToDocument(JTextPane text) {
		text.setEditable(false);
		
        String[] initString =
            { "Sign up for all the latest ",            //regular
              " ",                                      //icon
              "news,\noffers, and ", 					//regular
              "30% ",                                   //bold
              "off your first order.", 					//regular
             };

        String[] initStyles =
            { "regular", "icon", "regular", 
              "bold", "regular" 
            };

        StyledDocument doc = text.getStyledDocument();
        addStylesToDocument(doc);
	        try {
	            for (int i=0; i < initString.length; i++) {
	                doc.insertString(
	                		doc.getLength(), initString[i], doc.getStyle(initStyles[i]));
	            }
	        } catch (BadLocationException e) {
	            System.err.println("Couldn't insert initial text into text pane.");
	        }		
	}

    protected void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setFontFamily(def, "Segoe UI"); //"SansSerif");

        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontSize(regular, 16);

        Style s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);

        s = doc.addStyle("icon", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        Icon lifeicon = getIcon("lifeicon_small.png");
	        if (lifeicon != null) {
	            StyleConstants.setIcon(s, lifeicon);
	        }
    }
    
	private static final long serialVersionUID = 1L;

	public Icon getRadioselected() {
		if (radioselected == null)
			radioselected = this.getIcon("radio_selected.png");
		return radioselected;
	}

	public Icon getRadiounselected() {
		if (radiounselected == null)
			radiounselected = this.getIcon("radio_unselected.png");
		return radiounselected;
	}

}
