package org.cuong.intro.gwt.client.model;

/**
 * Interface for change notifier.
 * 
 * @author ccollins
 * 
 */
public interface CalculatorChangeNotifier {
    public void addChangeListener(final CalculatorChangeListener listener);
}
