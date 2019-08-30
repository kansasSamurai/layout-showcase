package org.jwellman.swing.jtextfield;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import org.jwellman.swing.borders.RoundedCornerBorder;

/**
 * Inspired by:
 * https://java-swing-tips.blogspot.com/2012/03/rounded-border-for-jtextfield.html
 * 
 * @author Rick Wellman
 *
 */
@SuppressWarnings("serial")
public class RoundedTextField extends JTextField implements FocusListener {

	private Color colorFocusLost;
	
	private Color colorFocusGained = new Color(0xF57C00);
	
	private Color colorFocusBackground = new Color(0xefefef);
	
	private RoundedCornerBorder _rcb;
	
	public RoundedTextField() {
		super();
		this.init();
	}
	
	public RoundedTextField(int numchar) {
		super(numchar);
		this.init();
	}
	
	public RoundedTextField(String string) {
		super(string);
		this.init();
	}
	
	private void init() {
		this.addFocusListener(this);
	}

// This was an attempt to fix the "non uniform" size of the
// actual "text field" portion of this component; it didn't work.
// super.getPreferredSize() includes border(s) in its calculation
// so all this does is increase the outer bounds by 2pixels.
//	@Override
//	public Dimension getPreferredSize() {
//		final Dimension d = super.getPreferredSize();
//		d.setSize(d.getWidth(), d.getHeight()+2);
//		return d;		
//	}
	
	@Override
	protected void paintComponent(Graphics g) {
	 // if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
	 // Note: I originally include the instanceof operator that you see above;
	 // however, it is defeated if you install/add another border for a 
	 // compound border.  I think removing it should be ok.
	 // Generally, it was ok to remove it... however, I also had to remove
	 // the call to getBorder() below and augment this class design by
	 // adding a new instance variable:  _rcb
		
		if (!isOpaque()) {
			// final RoundedCornerBorder b = (RoundedCornerBorder) getBorder();			
			
			final Graphics2D g2 = (Graphics2D) g.create();
			g2.setPaint(getBackground());
			g2.fill(_rcb.getBorderShape( 0, 5, getWidth() - 1, getHeight() - 13));

			g2.dispose();
		}
		super.paintComponent(g);
	}

	@Override
	public void updateUI() {
		System.out.println("RTB::updateUI()");
		super.updateUI();		
		setOpaque(false);
		setBorder(_rcb = new RoundedCornerBorder(this));
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (colorFocusLost == null) colorFocusLost = this.getCaretColor();
		this.setCaretColor(colorFocusGained);
		this.setBackground(colorFocusBackground);
		this.repaint();
	}

	@Override
	public void focusLost(FocusEvent e) {
		// this.setCaretColor(colorFocusLost);
		this.setBackground(Color.white);
		this.repaint();
	}

}
