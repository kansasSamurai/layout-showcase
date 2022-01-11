package org.jwellman.swing.layout;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Constraint object for the FluidLayout.
 * 
 * @author Rick
 *
 */
public class FluidConstraint implements Cloneable {

    public int xs = 12;
    public int sm = 6;
    public int med = 4;
    public int lg = 3;
    public int xl = 2;
    
    /**
     * Default Constructor
     * 
     * Default constraints are: 12,6,4,3,2
     */
    public FluidConstraint() {
        // Intentionally Empty
    }
    
    public FluidConstraint(int xs, int sm, int med, int lg, int xl) {
        this.xs = xs; this.sm = sm; this.med = med; this.lg = lg; this.xl = xl;
    }

    /**
     * Creates a copy of this fluid constraint.
     * 
     * @return a copy of this fluid constraint
     */
    public Object clone() {
        try {
            FluidConstraint c = (FluidConstraint)super.clone();
            return c;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

}
