package org.cuong.intro.gwt.client;

import org.cuong.intro.gwt.client.model.operator.AbstractOperator;
import org.cuong.intro.gwt.client.model.operator.OperatorAdd;
import org.cuong.intro.gwt.client.model.operator.OperatorDivide;
import org.cuong.intro.gwt.client.model.operator.OperatorInvert;
import org.cuong.intro.gwt.client.model.operator.OperatorMultiply;
import org.cuong.intro.gwt.client.model.operator.OperatorPower;
import org.cuong.intro.gwt.client.model.operator.OperatorSqrt;
import org.cuong.intro.gwt.client.model.operator.OperatorSubtract;

public class TestConstants {
    public static final AbstractOperator OP_ADD = new OperatorAdd();

    public static final AbstractOperator OP_SUBTRACT = new OperatorSubtract();

    public static final AbstractOperator OP_MULTIPLY = new OperatorMultiply();

    public static final AbstractOperator OP_DIVIDE = new OperatorDivide();

    public static final AbstractOperator OP_POWER = new OperatorPower();

    public static final AbstractOperator OP_INVERT = new OperatorInvert();

    public static final AbstractOperator OP_SQRT = new OperatorSqrt();
}
