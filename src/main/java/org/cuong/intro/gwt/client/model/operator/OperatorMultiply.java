package org.cuong.intro.gwt.client.model.operator;

import org.cuong.intro.gwt.client.CalculatorConstants;
import org.cuong.intro.gwt.client.model.CalculatorData;

public class OperatorMultiply extends BinaryOperator {
    public OperatorMultiply() {
        super(CalculatorConstants.MULTIPLY);
    }

    public void operate(final CalculatorData data) {
        data.setDisplay(String.valueOf(data.getBuffer() * Double.parseDouble(data.getDisplay())));
        data.setInitDisplay(true);
    }
}
