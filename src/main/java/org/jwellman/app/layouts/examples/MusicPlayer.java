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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.jwellman.app.layouts.buttons.AFlatButton;
import org.jwellman.foundation.swing.XCheckBox;
import org.jwellman.swing.event.FocusAdapterPersistentSelection;
import org.jwellman.swing.jbutton.RolloverButton;
import org.jwellman.swing.jlabel.ActionLabel;
import org.jwellman.swing.jpanel.RestrictedHeightPanel;
import org.jwellman.swing.jtextfield.RoundField;
import org.jwellman.swing.jtextfield.RoundedTextField;
import org.jwellman.swing.text.FancyCaret;

public class MusicPlayer extends ToolbarLayout {

	private static final long serialVersionUID = 1L;
	
	private static Color C_GRAY_MEDIUM = new Color(0xAEAEAE);

	protected static final Border matteGrayTop = BorderFactory.createMatteBorder(1, 0, 0, 0, C_GRAY_MEDIUM);
	protected static final Border matteGrayRight = BorderFactory.createMatteBorder(0, 0, 0, 1, C_GRAY_MEDIUM);
	protected static final Border matteGrayBottom = BorderFactory.createMatteBorder(0, 0, 1, 0, C_GRAY_MEDIUM);
	
	public MusicPlayer() {
		super();
		
		this.name = "Music Player";
	}

	protected Component createNorthComponent() {
		final JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.PAGE_AXIS));

		final JPanel pnlHeader = this.createHeader();
		final JPanel pnlMenuBar = this.createMenuBar();
		final JPanel pnlControls = this.createControls();

		north.add(pnlHeader);
		north.add(pnlMenuBar);
		north.add(pnlControls);
		return north;
	}

	private JPanel createControls() {
		final JPanel container = new JPanel();
		container.setLayout(new GridLayout(1,3));		
		container.setBorder(matteGrayBottom);
		
		JButton b; 
		JPanel p;
		
		p = new JPanel();
		p.add(label("[Type-Selector]"));
		container.add(p);

		p = new JPanel();
		p.add(label("[Card-Selector]"));
		container.add(p);

		p = new JPanel();
		//p.add(b = new JButton("Type-Selector"));
		container.add(p);

		return container;
	}

	private JPanel createMenuBar() {
		final JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		
		JMenu menu;
		JMenuBar mb = new JMenuBar();
		
		mb.add(menu = new JMenu("File"));
		menu.add(new JMenuItem("Exit"));
		
		mb.add(new JMenu("Edit"));
		mb.add(new JMenu("Song"));
		mb.add(new JMenu("View"));
		mb.add(new JMenu("Controls"));
		mb.add(new JMenu("Account"));
		mb.add(menu = new JMenu("Help"));
		menu.add(new JMenuItem("About"));
		container.add(mb, BorderLayout.NORTH);

		return container;
	}

	private JPanel createHeader() {		
		final JPanel container = new JPanel();
		container.setLayout(new GridLayout(1,3));
		container.setBorder(matteGrayBottom);
		
		JLabel label;
		
		label = label("[Rewind-Play-Forward]");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(label);
		
		label = label("[Company-Logo]");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(label);
		
		label = label("[Search]");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(label);
		
		return container;
	}
	
	protected Component createWesternComponent() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		container.setBorder(matteGrayRight);
		// p.setBackground(MEDIUM);
		
		container.add(new JLabel("Library"));
		container.add(new JCheckBox("Recently Added"));
		container.add(new JCheckBox("Artists"));
		container.add(new JCheckBox("Albums"));
		container.add(new JCheckBox("Songs"));
		container.add(new JCheckBox("Genre"));
		container.add(new JCheckBox("Downloaded"));

		JToggleButton b = new JToggleButton();
		//b.setIcon(defaultIcon);
		
		container.add(new JLabel("Music Playlists"));
		container.add(new JLabel("[] Genius"));
		container.add(new JLabel("[] Recently Added"));
		container.add(new JLabel("[] Recently Added"));
		container.add(new JLabel("[] Recently Added"));
		container.add(new JLabel("[] Recently Added"));
		container.add(new JLabel("[] Recently Added"));

//		JButton button;
//		
//		button = this.createButton(AFlatButton.PROFILE);
//		button.setText("Recently Added");
//		p.add(button);
//		
//		button = this.createButton(AFlatButton.LIBRARY);
//		button.setText("Artists");
//		p.add(button);
//		
//		button = this.createButton(AFlatButton.BUGREPORT);
//		button.setText("Albums");
//		p.add(button);
		
		return container;
	}

	protected Component createSouthComponent() {
		final JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setBorder(matteGrayTop);

		JPanel p = new JPanel();
		p.add(label("22 albums, 1 day, 2.73GB"));
		container.add(p);
		
		return container;
	}
	
	protected Component createCenterComponent() {
		final JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());

		JPanel p = new JPanel();
		p.add(label("[Album Item Selector]"));
		container.add(p);
		
		return container;
	}
	
	protected Component createEasternComponent() {
		return null;
	}

	
	
	
	
	private JLabel label(String title) {
		final JLabel label = new JLabel(title);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		return label;
	}
	
}
