package org.cuong.intro.gwt.client.model.operator;

import org.cuong.intro.gwt.client.model.CalculatorData;

/**
 * Base class for Calculator operators.
 * 
 * @author ccollins
 * 
 */
public abstract class AbstractOperator {
    public String label;

    AbstractOperator(final String label) {
        this.label = label;
    }

    public abstract void operate(final CalculatorData data);

    @Override
    public String toString() {
        return "Operator:" + this.label;
    }
}
