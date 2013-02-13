package org.cuong.intro.gwt.client.model;

import org.cuong.intro.gwt.client.model.operator.AbstractOperator;

public class CalculatorData implements CalculatorChangeNotifier {
    private String display;

    private double buffer;

    private boolean initDisplay = true;

    private boolean lastOpEquals;

    private AbstractOperator lastOperator;

    private CalculatorChangeListener listener;

    public CalculatorData() {
        this.display = "0";
    }

    public boolean isInitDisplay() {
        return initDisplay;
    }

    public void setBuffer(double buffer) {
        this.buffer = buffer;
    }

    public void setLastOpEquals(boolean lastOpEquals) {
        this.lastOpEquals = lastOpEquals;
    }

    public void setLastOperator(AbstractOperator lastOperator) {
        this.lastOperator = lastOperator;
    }

    public AbstractOperator getLastOperator() {
        return lastOperator;
    }

    public double getBuffer() {
        return this.buffer;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setInitDisplay(boolean b) {
        this.initDisplay = b;
        listener.onChange(this);
    }

    public void setDisplay(String valueOf) {
        this.display = valueOf;
        listener.onChange(this);
    }

    public boolean isLastOpEquals() {
        return this.lastOpEquals;
    }

    public void addChangeListener(CalculatorChangeListener calculatorChangeListener) {
        this.listener = calculatorChangeListener;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("***CalculatorData***\n");
        sb.append("display = " + this.display + "\n");
        sb.append("buffer = " + this.buffer + "\n");

        return sb.toString();
    }

    public void clear() {
        this.display = "0";
        this.buffer = 0.0;
        this.initDisplay = true;
        this.lastOpEquals = false;
        listener.onChange(this);
    }
}
