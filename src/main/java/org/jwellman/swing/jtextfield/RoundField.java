package org.jwellman.swing.jtextfield;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * Inspired by this post:
 * https://coderanch.com/t/336048/java/Border-rounded-JTextField
 * 
 * This is em... ok... but looks a bit amateurish...
 * maybe I can clean it up in the future
 * 
 * @param cols
 */
public class RoundField extends JTextField {
    
	public RoundField(int cols) {
        super(cols);
        // We must be non-opaque since we won't fill all pixels.
        // This will also stop the UI from filling our background.
        setOpaque(false);
        // Add an empty border around us to compensate for
        // the rounded corners.
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); //(3, 5, 3, 5));
        
        setBackground(new Color(0xF57C00));
    }
 
    protected void paintComponent(Graphics g) {
    	final Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
    	
        int width = getWidth(); System.out.print(width); System.out.print(", ");
        int height = getHeight(); System.out.println(height);
        // Paint a rounded rectangle in the background.
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 24, 24); // 8, 8
        g2.dispose();
        
        // Now call the superclass behavior to paint the foreground.
        super.paintComponent(g);
    }
    
}