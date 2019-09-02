package org.jwellman.app.layouts.examples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jwellman.swing.jbutton.RolloverToggleButton;
import org.jwellman.swing.jpanel.ImagePanel;
import org.jwellman.utility.Limit;

@SuppressWarnings("serial")
public class TransparentButtons extends ExampleAdapter implements ActionListener, KeyListener {

	private static final Color DARK_GRAY = new Color(0x77657b83, true);

	private JPanel container;
	private int x = 377;
	private int y = 234;
	private int SIZE_W = 31;
	private int SIZE_H = 15;
	private static final int ROWS = 6;
	private static final int COLS = 7;
	
	private ArrayList<String> backgrounds;

	public TransparentButtons() {
		super();
		this.setLayout(new GridBagLayout()); // (new BorderLayout());
		// this.setBorder(BorderFactory.createLineBorder(Color.yellow, 1));
		this.setFocusable(true); // required for key listener
		this.setFocusTraversalKeysEnabled(false);
		this.addKeyListener(this);
		
		this.backgrounds = new ArrayList<String>();
		this.backgrounds.add("sep-19-finding-jaguar-cal-640x480.jpg");
		this.backgrounds.add("sep-19-bear-time-cal-640x480.png");
		
		this.createContents();
		
		this.name = "Transparent Buttons";
		this.setImagePath("/images/examples/swing.gif");
		
	}
	
	private void createContents() {

		JPanel calendar = new ImagePanel("/images/calendar/" + this.backgrounds.get(1));
		calendar.setLayout(null);
		final Dimension d = new Dimension(640,480);
		calendar.setPreferredSize(d);
		calendar.setMinimumSize(d);
		calendar.setMaximumSize(d);
		
		container = new JPanel();
		container.setOpaque(false);
		container.setLayout(new GridLayout(ROWS,COLS)); // 6 week rows x 7 day columns
		for (int c=0; c < COLS; c++) {
			for (int r=0; r < ROWS; r++) {
				RolloverToggleButton b = new RolloverToggleButton("");
				//b.setForeground(DARK_GRAY);
				b.setRollColor(DARK_GRAY);
				b.setPressColor(Color.LIGHT_GRAY);
				b.setSelectedColor(DARK_GRAY);
					boolean border = false;
					if (border) {  // only for testing
						b.setBorderPainted(true);
						b.setBorder(BorderFactory.createLineBorder(Color.red));
					}
				b.setPreferredSize(new Dimension(20,20));
				container.add(b);			
			}
		}
		boolean border = false; int dx=0, dy=0;
		if (border) {  // only for testing
			container.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // only for testing
			dx=1; dy=1;
		}
		container.setBounds(x-dx, y-dy, SIZE_W*COLS + (dx*2), SIZE_H*ROWS + (dy*2));
		// 1 + 1 = 2 ; i.e. the 1px border I set; if the border is removed; remove the 2			

		calendar.add(container);
		
		this.add(calendar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.print(".typed");
		// keyTyped() is only invoked when a valid unicode characters is typed
		// invoke getKeyChar() = '?' because getKeyCode() will always be 0/unknown.

		System.out.print(" x = " + x);
		System.out.println(" y = " + y);
		container.setLocation(x,y);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(".press");
		
		y = (   e.getKeyCode() == KeyEvent.VK_UP)   ? Limit.decrementOf(y).to(0) 
			: ((e.getKeyCode() == KeyEvent.VK_DOWN) ? Limit.incrementOf(y).to(2000)
			: ((e.getKeyChar() == KeyEvent.VK_I)    ? Limit.decrementOf(y).by(10).to(0) 
			: ((e.getKeyChar() == KeyEvent.VK_K)    ? Limit.incrementOf(y).by(10).to(2000)
			: y	
			)));

		x = (   e.getKeyCode() == KeyEvent.VK_LEFT)  ? Limit.decrementOf(x).to(0) 
			: ((e.getKeyCode() == KeyEvent.VK_RIGHT) ? Limit.incrementOf(x).to(2000) 
			: ((e.getKeyChar() == KeyEvent.VK_J)     ? Limit.decrementOf(x).by(10).to(0) 
			: ((e.getKeyChar() == KeyEvent.VK_L)     ? Limit.incrementOf(x).by(10).to(2000)
			: x	
			)));

		System.out.print(" x = " + x);
		System.out.println(" y = " + y);
		container.setLocation(x,y);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println(".relsd");

	}

}
