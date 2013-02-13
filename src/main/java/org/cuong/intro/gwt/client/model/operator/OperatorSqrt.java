package org.cuong.intro.gwt.client.model.operator;

import org.cuong.intro.gwt.client.CalculatorConstants;
import org.cuong.intro.gwt.client.model.CalculatorData;

public class OperatorSqrt extends UnaryOperator {
    public OperatorSqrt() {
        super(CalculatorConstants.SQRT);
    }

    public void operate(final CalculatorData data) {
        data.setDisplay(String.valueOf(Math.sqrt(Double.parseDouble(data.getDisplay()))));
    }
}
