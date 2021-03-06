package org.jwellman.app.layouts.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class SimpleBorderLayout extends ExampleAdapter {

    protected static final Border BORDER_EMPTY = BorderFactory.createEmptyBorder(5, 5, 5, 5);

    protected static final Border BORDER_ETCHED = BorderFactory.createEtchedBorder();
    
    protected static final Border BORDER_LINE = BorderFactory.createLineBorder(Color.red, 1);    
    
    protected static final Border BORDER_DASHED = BorderFactory.createDashedBorder(null, 3.0f, 2.0f);
    
    protected static final Border BORDER_COMPOUND = BorderFactory.createCompoundBorder(BORDER_EMPTY, BORDER_ETCHED);
    
    protected static final Font FONT_SEGOE_UI = new Font("Segoe UI", Font.BOLD, 12);

    protected static final Color COLOR_GREY_MED = new Color(136,136,136);
    
    public SimpleBorderLayout() {
		this.setLayout(new BorderLayout());		
		
		Component c;
		
		if ((c = this.createCenterComponent()) != null)
			this.add(c, BorderLayout.CENTER);		
		if ((c = this.createEasternComponent()) != null)
			this.add(c, BorderLayout.EAST);
		if ((c = this.createWesternComponent()) != null)
			this.add(c, BorderLayout.WEST);
		if ((c = this.createNorthComponent()) != null)
			this.add(c, BorderLayout.NORTH);
		if ((c = this.createSouthComponent()) != null)
			this.add(c, BorderLayout.SOUTH);
		
		this.name = "Simple Border Layout";
		this.textDescription = "Long description goes here...";
		this.setImagePath("/images/examples/swing.gif");
//		this.setImagePath("/C:/dev/workspaces/my_sts_default/layout-showcase/src/main/resources/images/examples/swing.gif");
    }

	protected Component createSouthComponent() {
		return new JButton("South");
	}

	protected Component createNorthComponent() {
		return new JButton("North");
	}

	protected Component createWesternComponent() {
		return new JButton("West");
	}

	protected Component createEasternComponent() {
		return new JButton("East");
	}

	protected Component createCenterComponent() {
		return new JButton("Center");
	}
    
}
