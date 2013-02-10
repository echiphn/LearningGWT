package com.manning.gwtip.testme.client.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestPeopleModelData extends GWTTestCase
{
    public String getModuleName() {
        return "com.manning.gwtip.testme.TestMe";
    }

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testPeopleModelDataEvents()
    {
        // data
        PeopleModelData pd = new PeopleModelData();
        
        // component that can listen
        final Counter count = new Counter();
        pd.addPropertyChangeListener(new PropertyChangeListener(){
           public void propertyChange(PropertyChangeEvent e) 
           {               
               List people = (List) e.getNewValue();
               count.setCount(people.size());
           }
        });
        
        // add people a few times
        Person p1 = new Person("first1", "last1");
        pd.addPerson(p1);        
        Person p2 = new Person("f", "l");
        pd.addPerson(p2);
        Assert.assertEquals(2, count.getCount());
        
        // remove p1
        pd.removePerson(p1);
        Assert.assertEquals(1, count.getCount());
        
        // setPeople
        List newPeople = new ArrayList();
        newPeople.add(p1);
        newPeople.add(p2);
        pd.setPeople(newPeople);
        Assert.assertEquals(2, count.getCount());
        
    }
    
    private class Counter
    {
        private int count = 0;
        
        public int getCount()
        {
            return count;
        }
        
        public void setCount(int count)
        {
            this.count = count;
        }        
    }
 
}



