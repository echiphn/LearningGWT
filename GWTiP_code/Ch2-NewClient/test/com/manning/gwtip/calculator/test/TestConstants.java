package com.manning.gwtip.calculator.test;

import com.manning.gwtip.calculator.client.model.operator.AbstractOperator;
import com.manning.gwtip.calculator.client.model.operator.OperatorAdd;
import com.manning.gwtip.calculator.client.model.operator.OperatorDivide;
import com.manning.gwtip.calculator.client.model.operator.OperatorInvert;
import com.manning.gwtip.calculator.client.model.operator.OperatorMultiply;
import com.manning.gwtip.calculator.client.model.operator.OperatorPower;
import com.manning.gwtip.calculator.client.model.operator.OperatorSqrt;
import com.manning.gwtip.calculator.client.model.operator.OperatorSubtract;


public class TestConstants {
    public static final AbstractOperator OP_ADD = new OperatorAdd();
    public static final AbstractOperator OP_SUBTRACT = new OperatorSubtract();
    public static final AbstractOperator OP_MULTIPLY = new OperatorMultiply();
    public static final AbstractOperator OP_DIVIDE = new OperatorDivide();
    public static final AbstractOperator OP_POWER = new OperatorPower();
    public static final AbstractOperator OP_INVERT = new OperatorInvert();
    public static final AbstractOperator OP_SQRT = new OperatorSqrt();
}
