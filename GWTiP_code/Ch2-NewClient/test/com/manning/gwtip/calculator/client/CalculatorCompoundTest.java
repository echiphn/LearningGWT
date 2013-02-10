package com.manning.gwtip.calculator.client;

import junit.framework.Assert;

import com.manning.gwtip.calculator.client.controller.CalculatorController;
import com.manning.gwtip.calculator.client.model.CalculatorChangeListener;
import com.manning.gwtip.calculator.client.model.CalculatorData;
import com.manning.gwtip.calculator.test.AbstractTestCase;
import com.manning.gwtip.calculator.test.TestConstants;


public class CalculatorCompoundTest extends AbstractTestCase {
    //  data
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

        // keep performing the same operation on last digit
        controller.processDigit("1");
        controller.processOperator(TestConstants.OP_ADD);
        controller.processDigit("2");
        controller.processEquals();
        Assert.assertEquals("3.0", results.getDisplay());
        controller.processEquals();
        Assert.assertEquals("5.0", results.getDisplay());
        controller.processEquals();
        Assert.assertEquals("7.0", results.getDisplay());
        controller.processEquals();
        Assert.assertEquals("9.0", results.getDisplay());
        controller.processClear();
        
        // back to back equals and another operation after
        controller.processDigit("9");
        controller.processOperator(TestConstants.OP_DIVIDE);
        controller.processDigit("3");
        controller.processEquals();
        Assert.assertEquals("3.0", results.getDisplay());
        controller.processEquals();
        Assert.assertEquals("1.0", results.getDisplay());
        controller.processEquals();
        Assert.assertEquals("0.3333333333333333", results.getDisplay());
        controller.processOperator(TestConstants.OP_ADD);
        controller.processDigit("3");
        Assert.assertEquals("3", results.getDisplay());
        controller.processEquals();
        Assert.assertEquals("3.3333333333333335", results.getDisplay());
        controller.processClear();
        
        // disregard the first result on another calculation without clear
        controller.processDigit("1");
        controller.processOperator(TestConstants.OP_ADD);
        controller.processDigit("3");
        controller.processEquals();
        Assert.assertEquals("4.0", results.getDisplay());        
        controller.processDigit("1");
        controller.processDigit("4");
        controller.processDigit("5");        
        controller.processOperator(TestConstants.OP_SUBTRACT);
        controller.processDigit("3145");  
        controller.processEquals();
        Assert.assertEquals("-3000.0", results.getDisplay());
        controller.processClear();
        
        
        
       
    }
}
