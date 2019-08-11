package org.jwellman.app.layouts.examples;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.jwellman.swing.jpanel.GradientPanel;

public class GolfScore extends SimpleBorderLayout {

	private static final long serialVersionUID = 1L;

	private static final Color DARK = Color.DARK_GRAY;
	
	private static final Color MEDIUM = Color.GRAY;
	
	private static final Color LIGHT = Color.LIGHT_GRAY;
	
	private static final Font FONT_FOOTER = new Font("Arial Rounded MT Bold", Font.PLAIN, 10);
	
	private static final Font FONT_FOOTER_BOLD = new Font("Arial Rounded MT Bold", Font.BOLD, 10);	
	// Arial Rounded MT Bold , 12
	
	private static final Font FONT_TITLE = new Font("Arial Narrow", Font.BOLD, 28);
	// ***** Arial Narrow ! 
	// **    Arial Unicode MS << good look, poor spacing
	// ****  Bahnschrift << very good numbers, bad spacing on caps
	// ***   Bitstream Vera Sans Mono , bold
	// ***** Calibri , bold
	// ***   Consolas !! bold
	// ****  Ebrima , bold <<< good look , spacing is inconsistent
	// ***   Franklin Gothic Medium Cond , bold
	// **    Microsoft Sans Serif , bold << too skinny
	// ***** Segoe UI ? bold << this may have the best numbers
	
		private static final Border HEADTAIL = BorderFactory.createEmptyBorder(0,5,0,5);
	
	public GolfScore() {
		super();
		
		this.name = "Golf Score Panel";
		this.textDescription = "Long description goes here...";
		this.setImagePath("/images/examples/swing.gif");
	}
	
	protected Component createSouthComponent() {		
		JPanel panel = new JPanel();
		panel.setBackground(LIGHT);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		JLabel label = new JLabel("PRESENTED BY");
		label.setForeground(DARK);		
		label.setBorder(HEADTAIL);
		label.setFont(FONT_FOOTER);
		panel.add(label);
		
		label = new JLabel("ROCKET MORTGAGE BY QUICKEN LOANS");
		label.setForeground(DARK);		
		label.setFont(FONT_FOOTER_BOLD);
		panel.add(label);
		
		return panel;
	}

	protected Component createNorthComponent() {
		return new JPanel(new GridBagLayout());
	}

	protected Component createWesternComponent() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x557A95));
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

		JLabel label = new JLabel("LOGO");
		label.setBorder(HEADTAIL);
		label.setFont(FONT_TITLE);
		label.setForeground(Color.WHITE);
		panel.add(label);
		
		return panel;
	}
	
	protected Component createEasternComponent() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		JLabel label = new JLabel("-11");
		label.setFont(FONT_TITLE);
		label.setForeground(Color.WHITE);
		label.setBorder(HEADTAIL);
		panel.add(label);
		
		return panel;
	}
	
	protected Component createCenterComponent() {
		boolean useGradient = true;
		JPanel panel = useGradient 
				? new GradientPanel(new Color(0x557A95), new Color(0x7395AE)) 
				: new JPanel();
		if (useGradient) panel.setBackground(Color.BLUE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		
		JLabel label = new JLabel("RICKIE FOWLER");
		label.setForeground(Color.WHITE);
		label.setBorder(HEADTAIL);
		label.setFont(FONT_TITLE);
		panel.add(label);
		
		panel.add(Box.createHorizontalGlue());
		
		label = new JLabel("F");
		label.setForeground(Color.WHITE);
		label.setBorder(HEADTAIL);
		label.setFont(FONT_TITLE);
		panel.add(label);
		
		return panel;
	}
	
}
