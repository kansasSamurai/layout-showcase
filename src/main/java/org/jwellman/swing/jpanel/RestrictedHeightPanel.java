package org.jwellman.swing.jpanel;

import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * A JPanel extension that simply prevents most* parent layout managers
 * from manipulating its size by returning its preferred size when
 * its maximum size is requested.
 * [*] I think I meant this for panels within a boxlayout (vertical),
 *     but I'm having a hard time proving it; probably ResponsiveDashboard
 *     before I completely filled the "center" content area.
 *     
 * @author Rick
 *
 */
public class RestrictedHeightPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Dimension getMaximumSize() {
		final Dimension d = super.getMaximumSize();
		d.setSize(d.getWidth(), this.getPreferredSize().height);
		return d;
	}

}
