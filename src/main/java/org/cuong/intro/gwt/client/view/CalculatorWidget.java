package org.cuong.intro.gwt.client.view;

import org.cuong.intro.gwt.client.CalculatorConstants;
import org.cuong.intro.gwt.client.CalculatorController;
import org.cuong.intro.gwt.client.button.ButtonDigit;
import org.cuong.intro.gwt.client.button.ButtonOperator;
import org.cuong.intro.gwt.client.model.CalculatorChangeListener;
import org.cuong.intro.gwt.client.model.CalculatorData;
import org.cuong.intro.gwt.client.model.operator.OperatorAdd;
import org.cuong.intro.gwt.client.model.operator.OperatorDivide;
import org.cuong.intro.gwt.client.model.operator.OperatorInvert;
import org.cuong.intro.gwt.client.model.operator.OperatorMultiply;
import org.cuong.intro.gwt.client.model.operator.OperatorPower;
import org.cuong.intro.gwt.client.model.operator.OperatorSqrt;
import org.cuong.intro.gwt.client.model.operator.OperatorSubtract;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CalculatorWidget extends VerticalPanel {
    private final TextBox textBoxDisplay;

    public CalculatorWidget(final String title) {
        super();

        // instantiate the model
        final CalculatorData data = new CalculatorData();

        // instantiate the controller
        final CalculatorController controller = new CalculatorController(data);

        // Panel for components
        VerticalPanel p = new VerticalPanel();
        p.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        p.setStyleName(CalculatorConstants.STYLE_PANEL);

        // Grid for calculator layout
        Grid grid = new Grid(4, 5);
        grid.setStyleName(CalculatorConstants.STYLE_GRID);

        putTheDigitsInTheGrid(controller, grid);

        // put the operators in the grid
        putTheOperatorsInTheGrid(controller, grid);

        // add special button for clear (handled directly by controller)
        final Button clear = new Button(CalculatorConstants.CLEAR);
        clear.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                controller.processClear();
            }
        });
        clear.setStyleName(CalculatorConstants.STYLE_BUTTON);
        grid.setWidget(2, 4, clear);

        // add special button for equals (handled directly by controller)
        final Button equals = new Button(CalculatorConstants.EQUALS);
        equals.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                controller.processEquals();
            }
        });
        equals.setStyleName(CalculatorConstants.STYLE_BUTTON);
        grid.setWidget(3, 4, equals);

        // initialize the display textBox for results
        textBoxDisplay = new TextBox();

        // add a view change listener to the "data" model object
        // and if the model changes, update the view
        data.addChangeListener(new CalculatorChangeListener() {
            public void onChange(CalculatorData data) {
                textBoxDisplay.setText(String.valueOf(data.getDisplay()));
            }
        });
        textBoxDisplay.setText("0");
        textBoxDisplay.setTextAlignment(TextBox.ALIGN_RIGHT);

        // add the textBox and the grid to the panel, and the panel to the
        // widget
        p.add(textBoxDisplay);
        p.add(grid);
        this.add(p);
    }

    private void putTheOperatorsInTheGrid(final CalculatorController controller, Grid grid) {
        final Button divide = new ButtonOperator(controller, new OperatorDivide());
        grid.setWidget(0, 3, divide);

        final Button multiply = new ButtonOperator(controller, new OperatorMultiply());
        grid.setWidget(1, 3, multiply);

        final Button subtract = new ButtonOperator(controller, new OperatorSubtract());
        grid.setWidget(2, 3, subtract);

        final Button add = new ButtonOperator(controller, new OperatorAdd());
        grid.setWidget(3, 3, add);

        final Button sqrt = new ButtonOperator(controller, new OperatorSqrt());
        grid.setWidget(0, 4, sqrt);

        final Button power = new ButtonOperator(controller, new OperatorPower());
        grid.setWidget(1, 4, power);

        final Button invert = new ButtonOperator(controller, new OperatorInvert());
        grid.setWidget(3, 1, invert);
    }

    private void putTheDigitsInTheGrid(final CalculatorController controller, Grid grid) {
        final Button zero = new ButtonDigit(controller, "0");
        grid.setWidget(3, 0, zero);

        final Button one = new ButtonDigit(controller, "1");
        grid.setWidget(2, 0, one);

        final Button two = new ButtonDigit(controller, "2");
        grid.setWidget(2, 1, two);

        final Button three = new ButtonDigit(controller, "3");
        grid.setWidget(2, 2, three);

        final Button four = new ButtonDigit(controller, "4");
        grid.setWidget(1, 0, four);

        final Button five = new ButtonDigit(controller, "5");
        grid.setWidget(1, 1, five);

        final Button six = new ButtonDigit(controller, "6");
        grid.setWidget(1, 2, six);

        final Button seven = new ButtonDigit(controller, "7");
        grid.setWidget(0, 0, seven);

        final Button eight = new ButtonDigit(controller, "8");
        grid.setWidget(0, 1, eight);

        final Button nine = new ButtonDigit(controller, "9");
        grid.setWidget(0, 2, nine);

        final Button point = new ButtonDigit(controller, ".");
        grid.setWidget(3, 2, point);
    }
}
