package org.jwellman.swing.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager2;
import java.io.Serializable;
import java.util.Hashtable;

/**
 * A fluid layout manager for Java.
 * 
 * Note:  extending BorderLayout to set debug breakpoints and verify
 * the flow of logic called by the Swing engine.  After that,
 * I can remove the extension and continue writing this class 
 * as its design is intended. 1/3/2022
 * 
 * Inspired by Bootstrap grid.
 * 
 * @author rwellman
 *
 */
public class FluidLayout implements LayoutManager2, Serializable { // extends BorderLayout { // 

    private static final long serialVersionUID = 1L;
    
    /**
     * A default constraint when the user does not specify one.
     * Since fluid constraints are immutable, this can be final.
     * Though the default is unlikely to actually meet the user's need,
     * we choose to provide one so that code will actually work and not
     * throw an exception.
     */
    private static final FluidConstraint defaultConstraint = new FluidConstraint();

    private static final Dimension defaultDimension = new Dimension(0,0);

    private Hashtable<Component, FluidConstraint> comptable;
    
    private int gutter = 2;
    
    @SuppressWarnings("unused")
    private int breakpointCounter = 0;

    // === Constructors ===
    public FluidLayout() {
        comptable = new Hashtable<Component, FluidConstraint>();
    }

    // === Methods ===

    /**
     * Sets the constraints for the specified component in this layout.
     * 
     * @param comp the component to be modified
     * @param constraints the constraints to be applied
     */
    public void setConstraints(Component comp, FluidConstraint constraints) {
        comptable.put(comp, (FluidConstraint)constraints.clone());
    }

    private void out(String string, int value) {
        boolean debug = true; if (debug) {
            System.out.print(string);
            System.out.println(value);
        }
    }

    // === Properties ===

    // ===== LayoutManager interface
    
    /**
     * Adds the specified component to the layout.
     * Not used by this class.
     */
    @Override
    public void addLayoutComponent(String name, Component comp) {
        // Intentionally empty; not used by this class.
        breakpointCounter++; // super.addLayoutComponent(name, comp);
    }

    /**
     * Removes the specified component from the layout.
     * Not used by this class.
     */
    @Override
    public void removeLayoutComponent(Component comp) {
        // Intentionally empty; not used by this class.
        breakpointCounter++; // super.removeLayoutComponent(comp);
    }

    /**
     * RIX Notes:  So, as its name implies, this is called to get
     * the preferred size.  However, initial testing seems to indicate
     * that this method is only called during intial window display
     * during the pack() method.  It does not seem to get called
     * any time after that [Note 1].
     * 
     * Rather, the container's doLayout() method calls this object's
     * layoutContainer() method which seems free to do whatever it
     * wants.  
     * 
     * Note 1:  More research required, but I speculate that
     * this method is called whenever the container is invalidated()
     * which only happens if components are added/removed from
     * the container.
     * 
     */
    @Override
    public Dimension preferredLayoutSize(Container target) {
        System.out.print("fluid preferred: [ target: " + target.getSize());

        synchronized (target.getTreeLock()) {
            Dimension dim = new Dimension(0, 0);
            
            final int version = 3;
            switch (version) {
            case 1:
                breakpointCounter++; // dim = super.preferredLayoutSize(target); // only valid if extending an existing layout manager
                break;
            case 2:
                // for now (1/3/2022), just return a zero size
                // since I don't think it really matters since
                // we are ultimately going to layout based on 
                // available space in layoutContainer().
                // i.e. do nothing here because dim is already 0,0
                // update 1/4: this works when used with a stock JPanel
                // BUT... any overflow just gets truncated.  Ultimately,
                // this is only useful if we scroll the content when overflowed.
                // I have tried this with OverflowX(JPanel) and ScrollablePanel(JPanel)
                // but, as would be expected, they both call this method to achieve
                // their functionality (whereas normal JPanel does not).
                // This led me to implement case 3 (below) 
                dim = this.maximumLayoutSize(target);
                break;
            case 3:
                // for now (1/4/2022), see the update on case 2, but
                // this is used when using ScrollablePanel.  It is a current
                // workaround but really doesn't work.  When used with ScrollablePanel,
                // this needs to return the same dimensions as doLayout().  I am
                // pretty sure this is doable, I just need to implement it and test.
                dim = this.layoutOrCalculate(target, false);
                break;
            default:
                dim = target.getSize();
            }

            System.out.println(" ] " + dim);
            return dim;
        } // end synchronized
    }

    /**
     * For this layout, the minimum size is the preferred size
     */
    @Override
    public Dimension minimumLayoutSize(Container target) {
        //return this.preferredLayoutSize(target);
        breakpointCounter++; // return super.minimumLayoutSize(target);
        return this.preferredLayoutSize(target);
    }

    @Override
    public void layoutContainer(Container target) {
        Dimension dimThis = defaultDimension;
     
        final Dimension dimContainer = target.getSize();
        System.out.print("fluid layout: [ target: " + dimContainer);

        final int version = 2;
        switch (version) {
            case 1:
                breakpointCounter++; // super.layoutContainer(target); 
                break;
            case 2:
                dimThis = this.layoutOrCalculate(target, true); 
                break;
            default:
        }

        System.out.println(" ] " + dimThis);    
    }

    /**
     * This utility method is the workhorse of this layout manager.
     * It encapsulates the logic necessary to calculate the preferred size
     * which is also the same logic to layout the components.
     * 
     * @param target the target Container
     * @param layout true to layout the components ( i.e. setBounds() )
     * @return
     */
    private Dimension layoutOrCalculate(Container target, boolean layout) {

        final int N = target.getComponentCount();
        // final Insets insets = target.getInsets();

        final Dimension dimContainer = target.getSize();
        final int wc = dimContainer.width;

        // xsmall < 150
        // small < 300
        // medium < 500
        // large < 800
        // xlarge > 800

        final int maxGutter = gutter * 13;
        final int colWidth = (wc - maxGutter) / 12;
        final int rowHeight = 30; // TODO rowHeight will eventually be dynamic; height of tallest component in each row
        // since rows are also dynamic, this will require "row algorithm" is
        // executed prior to sizing/layout (similar to ResponsiveLayout)
        
        int top = gutter, left = gutter, bottom = rowHeight + gutter + gutter;
        int columnCount = 0; // keep track of columns in a row for wrapping
        boolean newrow = false;
        out("Starting new layout...", columnCount);
        for (int idxComponent=0; idxComponent < N; idxComponent++) {
            final Component c = target.getComponent(idxComponent);
            final FluidConstraint cc = comptable.get(c);

            out("Current ColumnCount  : ", columnCount);
            int cols = cc.xl; // default to xlarge constraint
            if (wc < 180) cols = cc.xs; else
            if (wc < 350) cols = cc.sm; else
            if (wc < 500) cols = cc.med; else
            if (wc < 800) cols = cc.lg;
            out(" + constraint columns: ", cols);

            columnCount += cols;
            out(" =    new ColumnCount: ", columnCount);

            if (columnCount > 12) {
                columnCount = cols; // reset the column counter
                out(" > OF new ColumnCount: ", columnCount);
                top += (rowHeight + gutter); // set the top of next row
                left = gutter; // set the left of next row

                newrow = true; // used to signal new bottom calculation at top of loop
                bottom += (rowHeight + gutter); // increase the bottom for the next row
            }

            final int gutters = cols - 1;
            final int w = (cols * colWidth) + (gutters * gutter);
                if (layout) {
                    c.setBounds(left, top, w, rowHeight);
                    // c.setSize() would be redundant because setBounds() sets the size according to the api docs                
                } else {
                    // do we need to do anything here for preffered size?
                }
            left += w + gutter; // set the left of next component

        }

        final Dimension dim = new Dimension(wc, bottom);
        return dim;
    }
    
    // ===== LayoutManager2 interface
    
    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        breakpointCounter++; // super.addLayoutComponent(comp, constraints);

        if (constraints == null) constraints = defaultConstraint;
        
        if (constraints instanceof FluidConstraint) {
            setConstraints(comp, (FluidConstraint)constraints);
        } else if (constraints != null) {
            throw new IllegalArgumentException("cannot add to layout: constraints must be a FluidConstraint");
        }

    }

    @Override
    public float getLayoutAlignmentX(Container target) {        
        return 0.5f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.5f;
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        // return this.preferredLayoutSize(target);
    }

    @Override
    public void invalidateLayout(Container target) {
        // Intentionally empty; not used by this class.
        breakpointCounter++; // super.invalidateLayout(target);
    }

}
