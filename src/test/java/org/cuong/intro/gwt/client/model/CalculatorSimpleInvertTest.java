package org.cuong.intro.gwt.client.model;

import org.cuong.intro.gwt.client.AbstractTestCase;
import org.cuong.intro.gwt.client.CalculatorController;
import org.cuong.intro.gwt.client.TestConstants;
import org.junit.Assert;

public class CalculatorSimpleInvertTest extends AbstractTestCase {
    // data
    CalculatorData results = new CalculatorData();

    public void test() {
        // instantiate data and controller
        CalculatorData data = new CalculatorData();
        data.addChangeListener(new CalculatorChangeListener() {
            public void onChange(CalculatorData data) {
                results = data;
            }
        });
        CalculatorController controller = new CalculatorController(data);

        controller.processDigit("1");
        controller.processOperator(TestConstants.OP_INVERT);
        Assert.assertEquals("-1.0", results.getDisplay());
        controller.processClear();

    }
}
