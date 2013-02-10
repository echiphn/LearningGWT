package com.manning.gwtip.testme.client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import junit.framework.Assert;

import com.google.gwt.junit.client.GWTTestCase;
import com.manning.gwtip.testme.client.controller.Controller;
import com.manning.gwtip.testme.client.model.PeopleModelData;
import com.manning.gwtip.testme.client.model.Person;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class GwtTestMyServiceServlet extends GWTTestCase
{

    // Play around with the timing to make the tests timeout and fail.
    // This tests your code AND checks up on the GWT delayTestFinish logic.
    //
    // (the exact timing that causes errors depends on the environment, and whether or not coverage is enabled)
    private static final int TEST_DELAY_1 = 5000;
    private static final int TEST_DELAY_2 = 8000;

    public String getModuleName()
    {
        return "com.manning.gwtip.testme.TestMe";
    }

    public void setUp() throws Exception
    {
        super.setUp();
    }

    public void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testAddOrUpdatePerson1()
    {
        Person person = new Person("Angus", "Young");

        PeopleModelData peopleData = new PeopleModelData();
        Controller controller = new Controller(peopleData);

        peopleData.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e)
            {
                List newPeople = (List) e.getNewValue();

                Assert.assertEquals(1, newPeople.size());
                finishTest();
            }
        });

        delayTestFinish(TEST_DELAY_1);
        controller.addOrUpdatePerson(person);
    }

    public void testAddOrUpdatePerson2()
    {
        Person person = new Person("Bon", "Scott");

        PeopleModelData peopleData = new PeopleModelData();
        Controller controller = new Controller(peopleData);

        peopleData.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e)
            {
                List newPeople = (List) e.getNewValue();

                Assert.assertEquals(2, newPeople.size());
                finishTest();
            }
        });

        delayTestFinish(TEST_DELAY_2);
        controller.addOrUpdatePersonSlow(person);
    }
}
