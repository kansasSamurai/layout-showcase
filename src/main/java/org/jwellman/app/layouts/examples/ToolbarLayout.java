package org.jwellman.app.layouts.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import org.jwellman.app.layouts.buttons.AFlatButton;
import org.jwellman.app.layouts.buttons.FlatButtonBasic;

public class ToolbarLayout extends SimpleBorderLayout implements ActionListener {

	private static final long serialVersionUID = 1L;

	protected static final Color DARK = Color.DARK_GRAY;
	
	protected static final Color MEDIUM = Color.GRAY;
	
	protected static final Border DEBUG_BORDER = BorderFactory.createLineBorder(Color.magenta);
	
	public ToolbarLayout() {
		super();
		
		this.name = "Toolbar Layout";
	}
	
	protected Component createSouthComponent() {		
		JPanel panel = (JPanel) this.createPanel("South", true);
		panel.setBackground(DARK);
		return panel;
	}

	protected Component createNorthComponent() {
		JPanel panel = (JPanel) this.createPanel("North", true);
		panel.setBackground(DARK);
		return panel;
	}

	protected Component createWesternComponent() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.setBackground(MEDIUM);
		
		JButton button = this.createButton(AFlatButton.PROFILE);
		p.add(button);
		
		button = this.createButton(AFlatButton.LIBRARY);
		p.add(button);
		
		p.add(Box.createVerticalGlue());
		
		button = this.createButton(AFlatButton.BUGREPORT);
		p.add(button);
		
		return p;
	}

	protected Component createEasternComponent() {
		return this.createPanel("East", false);
	}

	protected Component createCenterComponent() {
		return this.createPanel("Center", false);
	}

	protected JButton createButton(String action) {
		JButton button = null;
		
		int version = 1;
		switch (version) {
		case 1:
			button = new FlatButtonBasic(action);
			break;
		case 2:
			break;
		}
		
		button.addActionListener(this);		
		return button;
	}
	
	protected Component createPanel(String string, boolean showBorder) {
		boolean addSecondButton = false;
		boolean useDefaultLayout = false; // true means use FlowLayout (the JPanel default)
		
		JPanel p = new JPanel();
		if (showBorder)
			p.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));
			// p.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		if (!useDefaultLayout)
			p.setLayout(new GridBagLayout());
		
		JButton button = new JButton(string);
		p.add(button);
		
		if (addSecondButton) {
			button = new JButton(string);
			p.add(button);
		}

		return p;
	}

	private boolean decorated = false;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (decorated) {
			JOptionPane.showMessageDialog(this, "Your action works");			
		} else {
			decorated = true;
			this.decoratePanels(this);
			this.repaint();			
		}
	}

	/**
	 * Starting with (but not including) this component,
	 * for every child component that is a JPanel, add a title border.
	 * 
	 * Also, if the child is a JPanel, recursively call this method
	 * so that any of its JPanel children are decorated as well.
	 * 
	 * @param parent
	 */
	protected void decoratePanels(JComponent parent) {
		boolean parenthasborderlayout = false;
		JComponent north = null, south = null, east = null, west = null, center = null;
		
		if (parent instanceof JPanel) {
			final LayoutManager layout = ((JPanel) parent).getLayout();
			if (layout instanceof BorderLayout) {
				parenthasborderlayout = true;
				final BorderLayout brd = (BorderLayout) layout;
				north  = (JComponent) brd.getLayoutComponent(BorderLayout.NORTH);
				south  = (JComponent) brd.getLayoutComponent(BorderLayout.SOUTH);
				east   = (JComponent) brd.getLayoutComponent(BorderLayout.EAST);
				west   = (JComponent) brd.getLayoutComponent(BorderLayout.WEST);
				center = (JComponent) brd.getLayoutComponent(BorderLayout.CENTER);
			}
		}
		
		for (Component child : parent.getComponents()) {
			final JComponent jcomp = (JComponent) child;
			
			String suffix = "";
			if (parenthasborderlayout) {
				if (child.equals(north))  suffix = " (N)";
				if (child.equals(south))  suffix = " (S)";
				if (child.equals(east ))  suffix = " (E)";
				if (child.equals(west ))  suffix = " (W)";
				if (child.equals(center)) suffix = " (C)";
			}

			if (child instanceof JPanel) {
				final LayoutManager layout = ((JPanel) child).getLayout();

				final String title = layout.getClass().getSimpleName() + suffix;
				this.addBorder(jcomp, BorderFactory.createTitledBorder(title));
				this.decoratePanels(jcomp);
			} else if (child instanceof JScrollPane) {
				final JScrollPane sp = (JScrollPane)child;
				final String title = "JScrollPane" + suffix;
				this.addBorder(jcomp, BorderFactory.createTitledBorder(title));
				
				this.decoratePanels( (JComponent) sp.getViewport().getComponent(0) );
			} else {
				
				// Some buttons have the "border painted" turned off by default,
				// others have it turned off by the user. We want the border
				// always painted since that is the purpose of this utility method.
				if (child instanceof AbstractButton)
					((AbstractButton)child).setBorderPainted(true);
				
				this.addBorder(jcomp, DEBUG_BORDER);
			}
		}
	}
	
	protected void addBorder(JComponent c, Border b) {
		if (c.getBorder() != null) {
			final Border bb = BorderFactory.createCompoundBorder(b, c.getBorder());
			c.setBorder(bb);
		} else {
			c.setBorder(b);
		}
	}

}
