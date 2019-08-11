package org.jwellman.swing.borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;

/**
 * 
 * @author Rick Wellman
 *
 */
@SuppressWarnings("serial")
public class RoundedCornerBorder extends AbstractBorder {

	@SuppressWarnings("unused")
	private static final Color ALPHA_ZERO = new Color(0x000000, true);

	private static final Color ORANGE = new Color(0xF57C00); // cb4b16
	
	private JComponent wrapped;
	
	public RoundedCornerBorder() {
		super();
	}
	
	public RoundedCornerBorder(JComponent c) {
		super();
		this.wrapped = c;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		
		final Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		System.out.print("y:" + y + ",h:" + height);
		final Shape border = getBorderShape(x, y+5, width-1, height-13); // I like this even better... text hilighting still sucks though :(
//		final Shape border = getBorderShape(x, y+4, width-1, height-11); << this is good... just seeing if I can get it a "little" better
//		final Shape border = getBorderShape(x, y, width - 1, height - 1); // < original
//		final Area corner = new Area(new Rectangle2D.Double(x, y, width, height));
//		corner.subtract(new Area(border));
//		g2.setPaint(ALPHA_ZERO);
//		g2.fill(corner);
		
		g2.setPaint(Color.gray);
			if ( c != null && c.hasFocus() ) g2.setPaint(ORANGE);
		
		g2.draw(border);
		
		g2.dispose();
	}

	private static final int TB = 8; // 8
	private static final int LR = 8; // 4
	
	// TODO 
	// Q: Shouldn't this be private?  If so, need to remove from RoundedTextField
	// A: Well, the custom textfield uses this to paint the background color
	//    so the answer, for now, is 'no'
	public Shape getBorderShape(int x, int y, int w, int h) {
		int r = h; // = h-2; // This is semi-circular end caps
		System.out.println(",r:" + r);
		// h-4 works, but h-2 looks a little better
		//  h / 2; // This looks more like rounded corners
		return new RoundRectangle2D.Double(x, y, w, h, r, r);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(TB, LR, TB, LR);
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.set(TB, LR, TB, LR);
		return insets;
	}
	
}
