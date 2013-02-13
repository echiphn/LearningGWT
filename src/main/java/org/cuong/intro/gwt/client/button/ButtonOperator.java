package org.cuong.intro.gwt.client.button;

import org.cuong.intro.gwt.client.CalculatorConstants;
import org.cuong.intro.gwt.client.CalculatorController;
import org.cuong.intro.gwt.client.model.operator.AbstractOperator;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;

public class ButtonOperator extends Button {
    public ButtonOperator(final CalculatorController controller, final AbstractOperator op) {
        super(op.label);
        this.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                controller.processOperator(op);
            }
        });
        this.setStyleName(CalculatorConstants.STYLE_BUTTON);
    }
}
