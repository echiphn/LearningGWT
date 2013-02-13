package org.cuong.intro.gwt.client.model;

import org.cuong.intro.gwt.client.AbstractTestCase;
import org.cuong.intro.gwt.client.CalculatorController;
import org.cuong.intro.gwt.client.TestConstants;
import org.junit.Assert;

public class CalculatorSimpleDivideTest extends AbstractTestCase {
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

        controller.processDigit("9");
        controller.processOperator(TestConstants.OP_DIVIDE);
        controller.processDigit("3");
        controller.processEquals();
        Assert.assertEquals("3.0", results.getDisplay());
        controller.processClear();

        controller.processDigit("9");
        controller.processOperator(TestConstants.OP_DIVIDE);
        controller.processDigit(".");
        controller.processDigit("3");
        controller.processEquals();
        Assert.assertEquals("30.0", results.getDisplay());
        controller.processClear();

        controller.processDigit("999");
        controller.processDigit(".");
        controller.processDigit("75");
        controller.processOperator(TestConstants.OP_DIVIDE);
        controller.processDigit("3");
        controller.processDigit(".");
        controller.processDigit(".");
        controller.processDigit("25");
        controller.processEquals();
        Assert.assertEquals("307.61538461538464", results.getDisplay());
        controller.processClear();
    }
}
