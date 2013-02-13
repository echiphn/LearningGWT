package org.cuong.intro.gwt.client;

import org.cuong.intro.gwt.client.view.CalculatorWidget;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Calculator implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new CalculatorWidget("Calculator"));
    }

}
