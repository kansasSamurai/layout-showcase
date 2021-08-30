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

	/**
	 * This override is necessary to achieve the desired effect when placed
	 * in a BoxLayout with PAGE_AXIS.  In particular, it restricts the maximum
	 * height to the preferred height, yet expands the maximum width so that
	 * the button will fill the container's width.  Also note that this behavior
	 * may not be desired when placed in containers with a different Layout Manager
	 * so this class/method would have to be altered to fit the use-case.
	 * <p>
	 * Here is an excerpt of the BoxLayout documentation default behavior:<br>
	 * When a BoxLayout lays out components from top to bottom, it tries to size 
	 * each component at the component's preferred height. If the vertical space 
	 * of the layout does not match the sum of the preferred heights, then 
	 * BoxLayout tries to resize the components to fill the space. 
	 * The components either grow or shrink to fill the space, with BoxLayout 
	 * honoring the minimum and maximum sizes of each of the components. 
	 * Any extra space appears at the bottom of the container.
	 * 
	 */
	@Override
	public Dimension getMaximumSize() {
	    Dimension size = getPreferredSize(); // default both (particularly the height) to the preferred size
	    size.width = Short.MAX_VALUE; // let the width expand to fit the container (assuming the container is concerned with the width)
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
		final ButtonModel model = this.getModel();

		final boolean fill = model.isPressed() || model.isRollover();
		if (fill) {
			Graphics2D g2 = (Graphics2D) g.create(); {
				final Rectangle r = this.getBounds();			
	            g2.setColor(model.isPressed() ? pressColor : rollColor);	            
		        g2.fillRect(0, 0, r.width, r.height );				
			} g2.dispose();
		} 

		super.paintComponent(g);
	}
	
}
