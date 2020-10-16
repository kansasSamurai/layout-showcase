package org.jwellman.app.layouts.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.jwellman.app.layouts.buttons.AFlatButton;
import org.jwellman.foundation.swing.XCheckBox;
import org.jwellman.swing.event.FocusAdapterPersistentSelection;
import org.jwellman.swing.jbutton.RolloverButton;
import org.jwellman.swing.jbutton.RolloverToggleButton;
import org.jwellman.swing.jlabel.ActionLabel;
import org.jwellman.swing.jpanel.RestrictedHeightPanel;
import org.jwellman.swing.jtextfield.RoundField;
import org.jwellman.swing.jtextfield.RoundedTextField;
import org.jwellman.swing.layout.ResponsiveLayout;
import org.jwellman.swing.text.FancyCaret;

/**
 * An example of a form layout; this class originated as a copy of 
 * ResponsiveDashboard2.java for convenience - it will likely be largely
 * different upon completion.
 * 
 * Like the ResponsiveDashboards, it is presented as the center panel 
 * within a ToolbarLayout to simply present a more finished example.
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
public class FormsPart01 extends ToolbarLayout implements ActionListener, ScrollPaneConstants {
	
	private static final long serialVersionUID = 1L;
	
	private static final boolean decorate = true;
	
	private static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);

	// ========== Palette ==========
	private static final Color ROLLOVER = new Color(0xefefef);

	private static final Color ROLLOVER_LIGHT = new Color(0xf8f8f8);
	
	private static final Color DARK_TEAL = new Color(0x016565);
	
	private static final Color TEAL = new Color(0x078e8e);
	
	// ========== SOLARIZED ==========
	private static final Color ORANGE = new Color(0xF57C00); // cb4b16
	
	private static final Color DARK_GRAY = new Color(0x657b83);
	
	private static final Color DARKER_GRAY = new Color(0x586e75);
	
	private static final Color LIGHTEST_BACKGROUND = new Color(0xfdfdfd); // 0xfdf6e3

	// ========== BORDERS ==========
	private static final Border BUTTON_BORDER = BorderFactory.createEmptyBorder(10,10,10,10);

	private static final Border CBUTTON_BORDER = BorderFactory.createEmptyBorder(1,1,1,1); //(2,2,2,2);

	private static final Border TABPANEL_BORDER = BorderFactory.createMatteBorder(0, 0, 1, 0, ROLLOVER);

	private static final Border FOOTER_BORDER = BorderFactory.createMatteBorder(1, 0, 0, 0, DARK_GRAY);
	
	private static final Border SELECTEDTAB_BORDER = BorderFactory.createMatteBorder(0, 0, 3, 0, ORANGE);
	
	private static final Border UNSELECTEDTAB_BORDER = BorderFactory.createMatteBorder(0, 0, 3, 0, ROLLOVER);
	
	// ========== FONTS ==========
	private static final Font LABEL_FONT = new Font("Calibri", Font.BOLD, 12);
	
	private static final Font CONTENT_FONT = new Font("Calibri", Font.PLAIN, 12);
	
	// Now that we are using HTML text, no need to use BOLD as the default
	private static final Font BUTTON_FONT = new Font("Calibri", Font.PLAIN, 24);

	private static final Font TAB_FONT = new Font("Calibri", Font.PLAIN, 18);

	private static final Font VERDANA = new Font("Verdana", Font.PLAIN, 10);
	
	private static final Font VERDANA2 = new Font("Verdana", Font.PLAIN, 14);
	
	private static final Font VERDANA3 = new Font("Verdana", Font.BOLD, 18);
	
	// ========== ICONS ==========
	private Icon iconBlackBook;
	
	private Icon iconWhiteBook;
	
	public FormsPart01() {
		super();
		
		this.name = "Forms Part 01";
	}

	protected Component createCenterComponent() {
		final JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));		
		
		JPanel panel, p2, north, south, west, center; // reusable panel reference;
		JLabel label = null; // reusable label reference;
		JButton button = null; // reusable button reference;
		JCheckBox cbox = null; // reusable checkbox reference;
		JComponent tab = null; // reusable component reference;
		JTextField textfield = null; // reusable component reference;
		JScrollPane scroller = null; // reusable component reference;
		JRadioButton radio = null; // reusable component reference;
		ButtonGroup group = null; // reusable component reference;
		
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
		container.add( this.wrap(panel, null) );		
	
		// ==============================================
		boolean oldlayout = false;
		p2 = new JPanel(new BorderLayout());
		west = new JPanel(); west.setLayout(new BoxLayout(west, BoxLayout.PAGE_AXIS));
		north = new JPanel(); north.setLayout(new BoxLayout(north, BoxLayout.PAGE_AXIS));
		south = new JPanel(); south.setLayout(new FlowLayout(FlowLayout.RIGHT));
		center = new JPanel(); center.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
		p2.add(west, BorderLayout.WEST);
		p2.add(north, BorderLayout.NORTH);
		p2.add(south, BorderLayout.SOUTH);
		p2.add(center, BorderLayout.CENTER);
		if (oldlayout) {} else container.add(p2);
		// ==============================================
		
		// ========== NORTH ROW 1 ==========
	    panel = p();
//    	panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));		
	    	//if (decorate) this.addBorder(panel, BorderFactory.createEmptyBorder(10,0,0,0));
		    label = l("Test Name:"); 
		    spacer(label);
			panel.add(label);	

	    	textfield = new RoundedTextField(); // new RoundField(6); // new RoundedTextField("Thgpq");
		    spacer(textfield);
	    	textfield.setColumns(20);
	    	textfield.setText("GLUCOSE");
	    	textfield.setForeground(ORANGE);
	    	panel.add(textfield);
			
		if (oldlayout) container.add(panel); else north.add(panel);
		
		// ========== NORTH ROW 2 ==========
	    panel = p();
    	panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
//		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));		
	    	//if (decorate) this.addBorder(panel, BorderFactory.createEmptyBorder(10,0,0,0));
		    label = l("Test Code:");
//		    spacer(label);
			panel.add(label);	

	    	textfield = new RoundedTextField(); // new RoundField(6); // new RoundedTextField("Thgpq");
//		    spacer(textfield);
	    	textfield.setColumns(4);
	    	textfield.setText("01005");
	    	textfield.setForeground(ORANGE);
	    	panel.add(textfield);
			
	    	cbox = decorate ? cb("Active?") : new JCheckBox();
	    	cbox.setHorizontalTextPosition(SwingConstants.LEFT);
        	cbox.setBackground(Color.WHITE);
        	cbox.setForeground(DARK_GRAY);
	    	panel.add(cbox);

		if (oldlayout) container.add(panel); else north.add(panel);
		
		// ========== NORTH ROW 3 ==========
	    panel = p();
    	panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
//		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));		
	    	//if (decorate) this.addBorder(panel, BorderFactory.createEmptyBorder(10,0,0,0));
	    	//panel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
		    label = l("Starting:");
//		    spacer(label);
			panel.add(label);	

	    	textfield = new RoundedTextField(); // new RoundField(6); // new RoundedTextField("Thgpq");
//		    spacer(textfield);
	    	textfield.setColumns(6);
	    	textfield.setText("12-05-2020");
	    	textfield.setForeground(ORANGE);
	    	panel.add(textfield);
			
		    label = l("Until:");
//		    spacer(label);
			panel.add(label);	

	    	textfield = new RoundedTextField(); // new RoundField(6); // new RoundedTextField("Thgpq");
//		    spacer(textfield);
	    	textfield.setColumns(6);
	    	textfield.setText("07-22-2021");
	    	textfield.setForeground(ORANGE);
	    	panel.add(textfield);

		if (oldlayout) container.add(panel); else north.add(panel);
		
		// ========== WEST ==========
		panel = p();
//    	panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));		

		    label = l("RECURRENCE TYPE"); 
			panel.add(label);	
		
			group = new ButtonGroup();
			radio = radio(group, "One Time (Today)"); panel.add(radio); radio.setMnemonic(KeyEvent.VK_O);
			radio = radio(group, "One Time (Future)"); panel.add(radio); radio.setMnemonic(KeyEvent.VK_F);
			radio = radio(group, "Days"); panel.add(radio); radio.setMnemonic(KeyEvent.VK_D);			
			radio = radio(group, "Weeks"); panel.add(radio); radio.setMnemonic(KeyEvent.VK_W);
			radio = radio(group, "Months"); panel.add(radio); radio.setMnemonic(KeyEvent.VK_M);
			radio = radio(group, "Specific Days"); panel.add(radio); radio.setMnemonic(KeyEvent.VK_S);

		if (oldlayout) container.add(panel); else west.add(panel);

		// ========== CENTER ==========
		panel = p();
		panel.setLayout(new GridLayout(2,3));		

			label = l(" "); panel.add(label);	
		    label = l("How Often?"); panel.add(label);	
			label = l(" "); panel.add(label);	

			label = l("Every "); panel.add(label); label.setHorizontalAlignment(SwingConstants.RIGHT);
			textfield = new JTextField("20"); panel.add(textfield);	textfield.setHorizontalAlignment(JTextField.CENTER); textfield.setForeground(ORANGE);
			label = l(" Day(s)"); panel.add(label);	

		if (oldlayout) container.add(panel); else center.add(panel);

		panel = p();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));		

		    label = l("Which Days?"); 
			panel.add(label);	

			cbox = cbox("Monday"); panel.add(cbox);
			cbox = cbox("Tuesday"); panel.add(cbox);
			cbox = cbox("Wednesday"); panel.add(cbox);
			cbox = cbox("Thursday"); panel.add(cbox);
			cbox = cbox("Friday"); panel.add(cbox);        	

		if (oldlayout) container.add(panel); else center.add(panel);
		
		// ========== SOUTH ==========
		south.add(new JButton("Save"));
		south.add(new JButton("Cancel"));
    	
		if (oldlayout) {} else container.add(Box.createHorizontalGlue());

		return container;
	}

	private JCheckBox cbox(String string) {
		// TODO Auto-generated method stub
    	final JCheckBox cbox = decorate ? cb(string) : new JCheckBox();
    	// cbox.setHorizontalTextPosition(SwingConstants.LEFT);
    	cbox.setBackground(Color.WHITE);
    	cbox.setForeground(DARK_GRAY);
		return cbox;
	}

	private JRadioButton radio(ButtonGroup group, String title) {
		final JRadioButton b = new JRadioButton(title);
		b.setBackground(Color.white);
		group.add(b);
		return b;
	}

	private void spacer(JComponent c) {
		this.spacer(c, 5);
	}

	private void spacer(JComponent c, Integer gap) {
		this.addBorder(c, BorderFactory.createEmptyBorder(0, 0, 0, gap));
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
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));		
		
		// Reusable object refs
		JPanel panel, panel2;
		JLabel label;
		JButton button;
		//RolloverToggleButton rbutton;
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 0, 0);
		Border border = BorderFactory.createLineBorder(Color.white);
		
		panel = p(false);
		panel.setLayout(flow);
		panel.setBackground(DARK_TEAL);		
			label = l("September 2019");
			label.setForeground(Color.WHITE);
			label.setFont(VERDANA);
			panel.add(label);
		container.add(panel);
		
		panel = p(false);
		panel.setLayout(flow);
		panel.setBackground(TEAL);
			label = l("Monday");
			label.setForeground(Color.WHITE);
			label.setFont(VERDANA2);
			panel.add(label);
		container.add(panel);
				
		panel = p(false);
		panel.setLayout(flow);
		panel.setBackground(TEAL);
			label = l("4th");
			label.setForeground(Color.WHITE);
			label.setFont(VERDANA3);
			panel.add(label);
		container.add(panel);
				
		panel = p(false);
		panel.setBackground(TEAL);
		panel.setLayout(new BorderLayout());
			label = l("[]");
			label.setForeground(Color.WHITE);
			label.setFont(VERDANA2);
			panel.add(label, BorderLayout.WEST);

			label = l("[]");
			label.setForeground(Color.WHITE);
			label.setFont(VERDANA2);
			panel.add(label, BorderLayout.EAST);
		
			panel2 = new JPanel(new GridBagLayout());
			panel2.setOpaque(false);
				label = l("12:00 PM");
				label.setForeground(Color.LIGHT_GRAY);
				label.setFont(VERDANA);
				panel2.add(label);
			panel.add(panel2, BorderLayout.CENTER);
		
		container.add(panel);
				
		panel = p(false);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BorderLayout());
			label = l("<");
			// label.setForeground(Color.WHITE);
			label.setFont(VERDANA2);
			panel.add(label, BorderLayout.WEST);

			label = l(">");
			//label.setForeground(Color.WHITE);
			label.setFont(VERDANA2);
			panel.add(label, BorderLayout.EAST);
		
			panel2 = new JPanel(new GridBagLayout());
			panel2.setOpaque(false);
				label = l("September 2019");
				label.setFont(VERDANA);
				panel2.add(label);
			panel.add(panel2, BorderLayout.CENTER);
		
		container.add(panel);
		
		panel = p(false);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(1,7));
			button = b2("Sun"); button.setEnabled(false); panel.add(button);
			button = b2("Mon"); button.setEnabled(false); panel.add(button);
			button = b2("Tue"); button.setEnabled(false); panel.add(button);
			button = b2("Wed"); button.setEnabled(false); panel.add(button);
			button = b2("Thu"); button.setEnabled(false); panel.add(button);
			button = b2("Fri"); button.setEnabled(false); panel.add(button);
			button = b2("Sat"); button.setEnabled(false); panel.add(button);
		
		container.add(panel);
		
		for (int row = 0; row < 5; row++) {
			panel = p(false);
			panel.setBackground(Color.WHITE);			
			panel.setLayout(new GridLayout(1,7));
			for (int col = 1; col < 8; col++) {
				final int day = row*7+col;
				final String s = (day < 32) ? ("" + day) : "";
				button = b2(s); panel.add(button);
				if (day > 31) button.setEnabled(false);
			}
			
			container.add(panel);			
		}
		
		return container;
	}

	private JButton b2(String string) {
		Color d = (Color) UIManager.getDefaults().get("Button.disabledText");// .put("Button.disabledText")
		RolloverButton b = new RolloverButton(string);
		b.setFont(VERDANA);
		b.setHorizontalAlignment(CENTER);
		b.setBorder(CBUTTON_BORDER);
		b.setRollColor(d);
//		Insets inset = b.getBorder().getBorderInsets(b);
//		System.out.println(inset.toString());
		return b;
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
				rb.setCornerRadius(0);
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
        	// cbox.setFocusable(false);
        	cbox.setFont(LABEL_FONT);
        }

    	return cbox;
	}
	
	private JComponent a(JComponent c) {
		int version = 99;
		
		switch(version) {
		case 1: {
			JPanel p = new JPanel(new GridLayout());
			p.add(c);
			return p; }
		case 2: {
			JPanel p = new JPanel(new BorderLayout());
			p.add(c, BorderLayout.NORTH);
			return p; }
		default:
			return c;
		}
		
	}
	
	private JPanel p() {
		return p(true);
	}
	
	private JPanel p(boolean padleft) {
		final JPanel p = new RestrictedHeightPanel(); // JPanel();
		
		if (decorate) {
			p.setBackground(Color.WHITE);
			if (padleft)
				p.setBorder(BorderFactory.createEmptyBorder(0,15,0,0));			
		}
		
		return p;		
	}
	
	/** 
	 * Return your panel wrapped in a BorderLayout panel in the specified position.
	 * 
	 * @param p the JPanel to wrap
	 * @param position the BorderLayout position; if null, uses NORTH 
	 * @return
	 */
	private JPanel wrap(JPanel p, String position) {
		boolean enabled = false;
		if (enabled) {
			final JPanel wrapper = new JPanel(new BorderLayout());
			position = (position == null) ? BorderLayout.NORTH : position;
			wrapper.add(p, position);
			return wrapper;			
		} else {
			return p;
		}
	}
	
	private boolean decorated = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (decorated) {
			JOptionPane.showMessageDialog(this, "Your action works");			
		} else {
			decorated = true;
			this.decoratePanels(this);
			this.repaint();			
		}
	}

}
