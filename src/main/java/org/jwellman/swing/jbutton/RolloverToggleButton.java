package org.jwellman.swing.jbutton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

/**
 * 
 * @author rwellman
 *
 */
public class RolloverToggleButton extends JToggleButton {

	private static final long serialVersionUID = 1L;

	private static final Cursor HAND = new Cursor(Cursor.HAND_CURSOR);
	
	private static final RenderingHints HINTS = new RenderingHints( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
	
	private Color rollColor = new Color(0x6600CC); // 6600cc = purple
	
	private Color pressColor = new Color(0x6D6D6D); // 0x5200B8
	
	private Color selectedColor = new Color(0x6D6D6D);
	
	private int cornerRadius = 0;
	
	public RolloverToggleButton(String label, int radius) {
		super(label);
		
		this.cornerRadius = radius;
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.setFocusable(false);
		this.setCursor(HAND);		
	}
	
	public RolloverToggleButton(String label) {
		this(label, 0);
	}

//	@Override
//	public Dimension getMaximumSize() {
//	    Dimension size = getPreferredSize();
//	    size.width = Short.MAX_VALUE;
//	    return size;
//	}

	public Icon setIcon(String resource) {
        this.setIcon(new ImageIcon(this.getImage(resource)));
        return this.getIcon();
	}

	public Icon setPressedIcon(String resource) {
        this.setPressedIcon(new ImageIcon(this.getImage(resource)));
        return this.getIcon();
	}
	
	protected void decorate(String label) {		
		if (this.getText().isEmpty())
		this.setToolTipText(label); System.out.println(this.getText());
        this.setPressedIcon(new ImageIcon(this.getImage("/icons/actions/ic_book_white_24dp.png")));
	}

	protected final Image getImage(String resource) {
		try {
			final Image image = ImageIO.read(getClass().getResource(resource));
			System.out.println("height: " + image.getHeight(null) + " width: " + image.getWidth(null));
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		final Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHints(HINTS);

		final Rectangle r = this.getBounds(); 

		boolean paintme = false;
		final ButtonModel model = this.getModel();		
		if (model.isPressed()) {			
			// System.out.print(".ps");
            g2.setColor(pressColor); paintme = true;
		} else if (model.isRollover()) {
			//System.out.print(".ro");
            g2.setColor(rollColor); paintme = true;
		} else if (model.isSelected()) {
			//System.out.print(".xx");			
            g2.setColor(selectedColor); paintme = true;
		}
		if (paintme) {
            if (this.cornerRadius > 0) 
            	g2.fillRoundRect(0, 0, r.width, r.height, cornerRadius, cornerRadius);
            else 
            	g2.fillRect(0, 0, r.width, r.height );
		}

		g2.dispose();			
		
		super.paintComponent(g);
	}

	public Color getRollColor() {
		return rollColor;
	}

	public void setRollColor(Color rollColor) {
		this.rollColor = rollColor;
	}

	public Color getPressColor() {
		return pressColor;
	}

	public void setPressColor(Color pressColor) {
		this.pressColor = pressColor;
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}

	public int getCornerRadius() {
		return cornerRadius;
	}

	public void setCornerRadius(int cornerRadius) {
		this.cornerRadius = cornerRadius;
	}
	
}
