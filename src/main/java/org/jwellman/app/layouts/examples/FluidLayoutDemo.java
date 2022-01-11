package org.jwellman.app.layouts.examples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

import org.jwellman.swing.jpanel.OverflowX;
import org.jwellman.swing.jpanel.ScrollablePanel;
import org.jwellman.swing.layout.FluidConstraint;
import org.jwellman.swing.layout.FluidLayout;

@SuppressWarnings("serial")
public class FluidLayoutDemo extends ExampleAdapter {

    public FluidLayoutDemo() {
        this.name = "Fluid Layout";
        this.textDescription = "Long description goes here...";
        this.setImagePath("/images/examples/swing.gif");
//      this.setImagePath("/C:/dev/workspaces/my_sts_default/layout-showcase/src/main/resources/images/examples/swing.gif");
        this.setLayout(new BorderLayout());

        final ScrollablePanel p = new ScrollablePanel();
        p.setScrollableHeight( ScrollablePanel.ScrollableSizeHint.NONE );
        p.setScrollableWidth( ScrollablePanel.ScrollableSizeHint.FIT );
        p.setScrollableBlockIncrement(
            ScrollablePanel.VERTICAL, 
            ScrollablePanel.IncrementType.PERCENT, 200);

        final FluidConstraint fc = new FluidConstraint(12,2,2,2,2);
        p.setLayout(new FluidLayout());
        for (int i=0; i<50; i++) {
            if (i == 25 ) {
                p.add(new JButton("F"), fc); 
            } else {
                p.add(new JButton("Fluid")); 
            }
        }
        
        JScrollPane scrollPane = new JScrollPane(p);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);
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
