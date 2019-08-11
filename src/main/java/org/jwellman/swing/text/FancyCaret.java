package org.jwellman.swing.text;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

/**
 * http://www.java2s.com/Code/Java/Swing-JFC/Fanciercustomcaretclass.htm
 * 
 * @author Rick Wellman
 *
 */
@SuppressWarnings("serial")
public class FancyCaret extends DefaultCaret {

	@Override
	public void setSelectionVisible(boolean visible) {
		// This makes the selection visible even when the 
		// JTextComponent has lost focus
		super.setSelectionVisible(true);
	}

	@Override
	protected synchronized void damage(Rectangle r) {
		if (r == null) return;

		// Give values to x,y,width,height 
		// (inherited from java.awt.Rectangle)
		x = r.x;
		y = r.y;
		height = r.height;
		// A value for width was probably set by paint(), which we leave alone.
		// But the first call to damage() precedes the first call to paint(), 
		// so in this case we must be prepared to set a valid width, 
		// or else paint() will receive a bogus clip area and caret will not get drawn properly.
		if (width <= 0) 
			width = getComponent().getWidth();

		this.repaint(); // calls getComponent().repaint(x, y, width, height)
	}

	@Override
	public void paint(Graphics g) {

		final JTextComponent comp = getComponent();
			if (comp == null) return;

		final int dot = getDot();
		
		char dotChar;
		Rectangle r = null;
		try {
			r = comp.modelToView(dot);
			if (r == null) return;
			
			dotChar = comp.getText(dot, 1).charAt(0);
		} catch (BadLocationException e) {
			return;
		}

		final Graphics2D g2 = (Graphics2D) g.create();
		g2.setClip(null);
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		if ((x != r.x) || (y != r.y)) {
			// paint() has been called directly, without a previous call to damage(), so do some cleanup. 
			// (This happens, for example, when the text component is resized.)
			
			// erase previous location of caret
			this.repaint(); 
			
			// Update dimensions (width gets set later in this method)
			x = r.x; y = r.y;
			height = r.height;
		}
		
		g2.setColor(Color.lightGray); //(comp.getCaretColor());
	 // g2.setXORMode(comp.getBackground()); // original location of this line of code

		// Anywhere these appear, they were r.x, r.y, and r.height respectively.
		// These adjustments may not be applicable in all use cases, but they
		// are necessary for RoundedTextField/RoundedCornerBorder
		final int delta = 2;
		final int dx = r.x, dy = r.y - delta, dh = r.height + delta;
				
		if (dotChar == '\n') {
			int diam = dh;
			if (isVisible()) {
				g2.drawArc(dx - diam / 2, dy, diam, diam, 270, 180); // half circle
				g2.fillArc(dx - diam / 2, dy, diam, diam, 270, 180); // half circle
			}
			width = diam / 2 + 2;
			System.out.println("n");
			g2.dispose(); return;
		}

		if (dotChar == '\t')
			try {
				Rectangle nextr = comp.modelToView(dot + 1);
				if ((r.y == nextr.y) && (r.x < nextr.x)) {
					width = nextr.x - r.x;
					if (isVisible())
						g2.fillRoundRect(r.x, r.y, width, r.height, 12, 12);
					System.out.println("t");
					g2.dispose(); return;
				} else
					System.out.println("t2");
					dotChar = ' ';
			} catch (BadLocationException e) {
				dotChar = ' ';
			}

		width = g2.getFontMetrics().charWidth(dotChar);
		if (isVisible()) {
			g2.setXORMode(comp.getBackground());
			g2.fillRect(r.x, r.y, width, r.height);
		}

		g2.dispose(); return;

	}

}