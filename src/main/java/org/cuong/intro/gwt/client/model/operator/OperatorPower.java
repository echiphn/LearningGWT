package org.cuong.intro.gwt.client.model.operator;

import org.cuong.intro.gwt.client.CalculatorConstants;
import org.cuong.intro.gwt.client.model.CalculatorData;

public class OperatorPower extends BinaryOperator {
    public OperatorPower() {
        super(CalculatorConstants.POWER);
    }

    public void operate(final CalculatorData data) {
        data.setDisplay(String.valueOf(Math.pow(data.getBuffer(), Double.parseDouble(data.getDisplay()))));
        data.setInitDisplay(true);
    }
}
