package org.jwellman.app.layouts.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.jwellman.app.layouts.buttons.AFlatButton;
import org.jwellman.foundation.swing.XCheckBox;
import org.jwellman.swing.jbutton.RolloverButton;
import org.jwellman.swing.jlabel.ActionLabel;
import org.jwellman.swing.jpanel.OverflowX;

/**
 * An example of a dashboard layout.
 * This is a modification of DashboardLayout.java which uses
 * OverflowX.java with ResponsiveLayout.java 
 * (made AFTER the original ResponsiveDashboard.java)
 * 
 * It is presented as the center panel within a ToolbarLayout
 * to simply present a more finished example.
 * 
 * 
SOLARIZED HEX     16/8 TERMCOL  XTERM/HEX   L*A*B      RGB         HSB
--------- ------- ---- -------  ----------- ---------- ----------- -----------
base03    #002b36  8/4 brblack  234 #1c1c1c 15 -12 -12   0  43  54 193 100  21
base02    #073642  0/4 black    235 #262626 20 -12 -12   7  54  66 192  90  26
base01    #586e75 10/7 brgreen  240 #585858 45 -07 -07  88 110 117 194  25  46
base00    #657b83 11/7 bryellow 241 #626262 50 -07 -07 101 123 131 195  23  51
base0     #839496 12/6 brblue   244 #808080 60 -06 -03 131 148 150 186  13  59
base1     #93a1a1 14/4 brcyan   245 #8a8a8a 65 -05 -02 147 161 161 180   9  63
base2     #eee8d5  7/7 white    254 #e4e4e4 92 -00  10 238 232 213  44  11  93
base3     #fdf6e3 15/7 brwhite  230 #ffffd7 97  00  10 253 246 227  44  10  99
yellow    #b58900  3/3 yellow   136 #af8700 60  10  65 181 137   0  45 100  71
orange    #cb4b16  9/3 brred    166 #d75f00 50  50  55 203  75  22  18  89  80
red       #dc322f  1/1 red      160 #d70000 50  65  45 220  50  47   1  79  86
magenta   #d33682  5/5 magenta  125 #af005f 50  65 -05 211  54 130 331  74  83
violet    #6c71c4 13/5 brmagenta 61 #5f5faf 50  15 -45 108 113 196 237  45  77
blue      #268bd2  4/4 blue      33 #0087ff 55 -10 -45  38 139 210 205  82  82
cyan      #2aa198  6/6 cyan      37 #00afaf 60 -35 -05  42 161 152 175  74  63
green     #859900  2/2 green     64 #5f8700 60 -20  65 133 153   0  68 100  60
 * @author rwellman
 *
 */
@SuppressWarnings("unused")
public class ResponsiveDashboard2 extends ToolbarLayout implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private static final boolean decorate = true;
	
	private static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);

	private static final Color ROLLOVER = new Color(0xefefef);

	private static final Color ROLLOVER_LIGHT = new Color(0xf8f8f8);
	
	// ========== SOLARIZED ==========
	private static final Color ORANGE = new Color(0xF57C00); // cb4b16
	
	private static final Color DARK_GRAY = new Color(0x657b83);
	
	private static final Color DARKER_GRAY = new Color(0x586e75);
	
	private static final Color LIGHTEST_BACKGROUND = new Color(0xfdfdfd); // 0xfdf6e3

	// ========== BORDERS ==========
	private static final Border BUTTON_BORDER = BorderFactory.createEmptyBorder(10,10,10,10);

	private static final Border TABPANEL_BORDER = BorderFactory.createMatteBorder(0, 0, 1, 0, ROLLOVER);

	private static final Border FOOTER_BORDER = BorderFactory.createMatteBorder(1, 0, 0, 0, DARK_GRAY);
	
	private static final Border SELECTEDTAB_BORDER = BorderFactory.createMatteBorder(0, 0, 3, 0, ORANGE);
	
	private static final Border UNSELECTEDTAB_BORDER = BorderFactory.createMatteBorder(0, 0, 3, 0, Color.WHITE);
	
	// ========== FONTS ==========
	private static final Font LABEL_FONT = new Font("Calibri", Font.BOLD, 12);
	
	private static final Font CONTENT_FONT = new Font("Calibri", Font.PLAIN, 12);
	
	// Now that we are using HTML text, no need to use BOLD as the default
	private static final Font BUTTON_FONT = new Font("Calibri", Font.PLAIN, 24);

	private static final Font TAB_FONT = new Font("Calibri", Font.PLAIN, 18);

	private Icon iconBlackBook;
	
	private Icon iconWhiteBook;
	
	public ResponsiveDashboard2() {
		super();
		
		this.name = "Responsive Deux";
	}

	protected Component createCenterComponent() {
		final JPanel outer = new JPanel(new BorderLayout());
		
		final JPanel container = new OverflowX(); // new JPanel();
		//container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));		
		
		JPanel panel = null; // reusable panel reference;
		JLabel label = null; // reusable label reference;
		JButton button = null; // reusable button reference;
		JCheckBox cbox = null; // reusable checkbox reference;
		JComponent tab = null; // reusable component reference;
		
	    panel = p(false);
    	panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
    	panel.setBorder(TABPANEL_BORDER);
    		if (decorate) this.addBorder(panel, BorderFactory.createEmptyBorder(10,0,0,0));
		    tab = tab("Create New", true);
			panel.add(tab);		
		    tab = tab("Templates", false);
			panel.add(tab);		
		    tab = tab("API Network", false);
			panel.add(tab);		
		outer.add(panel, BorderLayout.NORTH); // container.add(panel);
	
	    panel = p();
	    	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    	if (decorate) this.addBorder(panel, BorderFactory.createEmptyBorder(10,0,0,0));
		    label = l("BUILDING BLOCKS");
			panel.add(label);		
		container.add(panel);
		
	    panel = p();
		panel.setLayout(new GridLayout(1,0));
			button = b("Request", "Create a basic request<br><br>");
			panel.add(button);
			button = b("Collection", "Save your requests in a collection for reuse and sharing");
			panel.add(button);
			button = b("Environment", "Save values you frequently use in an environment");
			panel.add(button);		
		container.add(panel);
		
	    panel = p();
	    	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		    label = l("ADVANCED");
			panel.add(label);		
		container.add(panel);
	
	    panel = p();
		panel.setLayout(new GridLayout(1,0));
			button = b("API&nbsp;Documentation", "Create and publish beautiful documentation");
			panel.add(button);
			button = b("Mock&nbsp;Server", "Create a mock server for your in-development APIs");
			panel.add(button);
			button = b("Monitor", "Scheduled automated tests and check performance of your APIs");
			panel.add(button);		
		container.add(panel);
		
	    panel = p();
	    	panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS)); //(new FlowLayout(FlowLayout.LEFT, 0, 5));
	    	if (decorate) this.addBorder(panel, BorderFactory.createEmptyBorder(10,0,0,0));
	    	label = l("Not sure where to start? Use a ");
	    	if (decorate) label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			panel.add(label);
			label = decorate ? new ActionLabel("template") : l("template");
			if (decorate) {
				label.setFont(LABEL_FONT);
				label.setForeground(ORANGE);
				ActionLabel aLabel = (ActionLabel)label;
				aLabel.addActionListener(this);
			}
			panel.add(label);		
			panel.add(l(" to see how Postman can help you in your work."));		
			panel.add(Box.createHorizontalGlue());
		container.add(panel);		

	    // The vertical glue does not work as well as adding 
	    // a panel with GridBagLayout():
	    // container.add(Box.createVerticalGlue());
	    panel = p();
			panel.setLayout(new GridBagLayout());
		container.add(panel);

	    panel = p();
	    	panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
	    	if (decorate) {
		    	this.addBorder(panel, BorderFactory.createEmptyBorder(0,5,0,0));
		    	this.addBorder(panel, FOOTER_BORDER);
		    	panel.setBackground(ROLLOVER_LIGHT);	    		
	    	}
	    	
//	        cbox = cb("Show this window on launch");
//	    	panel.add(cbox);
//	    	cbox = cb("Setting"); 
//	    		cbox.setFont(null);
//	    	panel.add(cbox);
	    	// This doesn't do anything visually when not using icons :(
	    	//cbox.setVerticalAlignment(SwingConstants.CENTER);
	    	
	    	panel.add(decorate ? cb("") : new JCheckBox());
	    	label = l("Show this window on launch");
	    	if (decorate) {
	    		label.setFont(CONTENT_FONT);
		    	this.addBorder(label, BorderFactory.createEmptyBorder(2,0,0,0));
	    	}
	    	panel.add(label);
			
	    	//panel.add(l("Show this window on launch"));
			panel.add(Box.createHorizontalGlue());
			
			button = b("Learn more with our Web Docs");
			button.setIcon(null); button.setPressedIcon(null);
			if (decorate) {
				button.setFont(LABEL_FONT);
				button.setForeground(ORANGE);				
			}
			panel.add(button);
		outer.add(panel, BorderLayout.SOUTH); // container.add(panel);

		JScrollPane scroll = new JScrollPane(container);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(null);
		outer.add(scroll, BorderLayout.CENTER);
		return outer; // container;
	}

	protected Component createNorthComponent() {
		JPanel p = new JPanel();
		p.setBackground(MEDIUM);

		JButton button = this.createButton(AFlatButton.PROFILE);
		button.setToolTipText(button.getText());
		button.setText(null);
		
		final int version = 2;
		switch (version) {
		case 1:
			// This version is ok but not sized ideally
			// due to the current implementation of FlatButtonBasic::getPreferredSize()
			p.setLayout(new BoxLayout(p, BoxLayout.LINE_AXIS));
			p.add(Box.createHorizontalGlue());			
			p.add(button);
			break;
		case 2:
			p.setLayout(new BorderLayout());
			p.add(button, BorderLayout.EAST);
			break;
		}
				
		return p;
	}
	
	protected Component createEasternComponent() {
		return new JPanel();
	}

	protected JLabel l(String text) {
		final JLabel label = new JLabel(text);
		if (decorate) {
			label.setForeground(DARK_GRAY);
			label.setFont(LABEL_FONT);			
		}
		
		return label;
	}
	
	protected JComponent tab(String text, boolean selected) {
		final int version = 3;
		JButton button = null;
		
		switch (version) {
		case 1:
			final JLabel label = new JLabel(text);
			if (decorate) {
			    label.setFont(TAB_FONT);
			    label.setBorder(selected ? SELECTEDTAB_BORDER : UNSELECTEDTAB_BORDER);
				label.setForeground(DARK_GRAY);			
			}
			
			return label;		
		case 2: // This actually was surprisingly really close
			button = new JButton(text);
			if (decorate) {
			    button.setFont(TAB_FONT);
			    button.setBorder(selected ? SELECTEDTAB_BORDER : UNSELECTEDTAB_BORDER);
				button.setForeground(DARK_GRAY);			
			}
			
			return button;		
		case 3:
			button = new JButton(text);
			if (decorate) {
				button = b(text);
			    button.setFont(TAB_FONT);
			    button.setBorder(BorderFactory.createEmptyBorder(3,3,0,3));
			    this.addBorder(button, selected ? SELECTEDTAB_BORDER : UNSELECTEDTAB_BORDER);
				button.setForeground(DARK_GRAY);
				// The following are actually default button values
				// but have to be "undone" due to the b() method:
				button.setIcon(null); 
				button.setSelectedIcon(null);
				button.setBorderPainted(true);
				
				RolloverButton rb = (RolloverButton)button;
				rb.setRollColor(new Color(0xFFCC80));
				rb.setCornerRadius(0); // 3
			}
			
			return button;		
		default:
				return null;
		}
	}
	
	private JButton b(String header, String detail) {
		StringBuilder b = new StringBuilder();
		
		int version = 2;
		switch (version) {
		case 1: // This works but has an annoying "padding" at the top
			b.append("<html><font color=#cb4b16 size=+2><b>"); // size=+1
			b.append(header);
			b.append("</b></font><br>");
			b.append(detail);
			b.append("</html>");
			break;
		case 2:
			b.append("<html>");
			b.append("<head><style type=\"text/css\">html, body {margin: -3 0 0 0;}</style></head><body>");
			// The margin in px depends on the size setting on the font in the next line
			b.append("<font color=#F57C00 size=+1><b>"); // size=+1			
			b.append(header);
			b.append("</b></font><br>");
			b.append(detail);
			b.append("</body></html>");
			break;
		default:
				
		}
		
		return b(b.toString());
	}

	protected JButton b(String label) {
		final int version = decorate ? 3 : 99;
		
		switch (version) {
		case 1: {
			final JButton b = new JButton(label);
			b.setBorderPainted(false);
			b.setFocusable(false);
			b.setCursor(HAND);
			
			return b;
		}
		case 2: {
			final RolloverButton b = new RolloverButton(label);
			b.setForeground(ORANGE);
			b.setRollColor(ROLLOVER);
			b.setPressColor(ROLLOVER);
			b.setBorder(BUTTON_BORDER);
			b.setFont(BUTTON_FONT);
			b.setBorderPainted(true);
			
			if (this.iconBlackBook == null) {				
				this.iconBlackBook = b.setIcon("/icons/actions/ic_book_black_24dp.png");
			} else {
				b.setIcon(this.iconBlackBook);
			}
			
			if (this.iconWhiteBook == null) {
				this.iconWhiteBook = b.setPressedIcon("/icons/actions/ic_book_white_24dp.png");				
			} else {
				b.setPressedIcon(this.iconWhiteBook);
			}

			return b;
		}
		case 3: {
			final RolloverButton b = new RolloverButton(label, 10);
			b.setForeground(DARK_GRAY);
			b.setRollColor(ROLLOVER);
			b.setPressColor(ROLLOVER);
			b.setBorder(BUTTON_BORDER);
			b.setFont(CONTENT_FONT);
			b.setVerticalTextPosition(SwingConstants.TOP);
			b.setHorizontalTextPosition(SwingConstants.RIGHT);
			b.setVerticalAlignment(SwingConstants.TOP);
			b.setHorizontalAlignment(SwingConstants.LEFT);

			if (this.iconBlackBook == null) {				
				this.iconBlackBook = b.setIcon("/icons/actions/ic_book_black_24dp.png");
			} else {
				b.setIcon(this.iconBlackBook);
			}
			
			if (this.iconWhiteBook == null) {
				this.iconWhiteBook = b.setPressedIcon("/icons/actions/ic_book_white_24dp.png");				
			} else {
				b.setPressedIcon(this.iconWhiteBook);
			}

			b.addActionListener(this);
			
			return b;
		}
		default:
			return new JButton(label);
		}
	}
	
	private JCheckBox cb(String label) {
        final JCheckBox cbox = 
            (JCheckBox) XCheckBox.create()
        		.setText(label)
        		//.setFont(CONTENT_FONT)
        		.get();
        if (decorate) {
        	cbox.setBackground(ROLLOVER_LIGHT);
        	cbox.setFocusable(false);
        	cbox.setFont(CONTENT_FONT);
        }

    	return cbox;
	}
	
	private JPanel p() {
		return p(true);
	}
	
	private JPanel p(boolean padleft) {
		final JPanel p = new JPanel();
		
		if (decorate) {
			p.setBackground(Color.WHITE);
			if (padleft)
				p.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));			
		}
		
		return p;		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Your action works");
	}
	
}
