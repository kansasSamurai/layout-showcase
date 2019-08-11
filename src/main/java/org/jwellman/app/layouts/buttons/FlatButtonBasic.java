package org.jwellman.app.layouts.buttons;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class FlatButtonBasic extends AFlatButton {

	private static final long serialVersionUID = 1L;

	private static final Color rollColor = new Color(0x6600CC); // 6600cc = purple
	
	private static final Color pressColor = new Color(0x6D6D6D); // 0x5200B8
	
	private static final Dimension DIMENSION = new Dimension(0,0);
	
	private static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);
	
	public FlatButtonBasic(String label) {
		super(label);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.setFocusable(false);
		this.setCursor(HAND);
		
		this.decorate(label);
	}

	@Override
	public Dimension getMaximumSize() {
	    Dimension size = getPreferredSize();
	    size.width = Short.MAX_VALUE;
	    return size;
	}
	
	private void decorate(String label) {		
		if (this.getText().isEmpty())
		this.setToolTipText(label); System.out.println(this.getText());
        this.setIcon(new ImageIcon(this.getImage(label)));
        this.setPressedIcon(new ImageIcon(this.getSelectedImage(label)));
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		
		final ButtonModel model = this.getModel();
		
		if (model.isPressed()) {
			final Rectangle r = this.getBounds(); 
			
            g2.setColor(pressColor);            
            g2.fillRect(0, 0, r.width, r.height );
            g2.dispose();
		} else if (model.isRollover()) {
			final Rectangle r = this.getBounds(); 
			System.out.print(".ro");
			
            g2.setColor(rollColor);            
            g2.fillRect(0, 0, r.width, r.height );
//          g2.fillRect(1, 1, r.width-2, r.height-2 );
            g2.dispose();			
		}
		
		super.paintComponent(g);
	}
	
}
