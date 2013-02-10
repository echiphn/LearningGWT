package com.manning.gwtip.testme.client.model;

import junit.framework.Assert;

import com.google.gwt.junit.client.GWTTestCase;
import com.manning.gwtip.testme.client.model.Person;

public class GwtTestPerson extends GWTTestCase
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
    
    // TODO leave this out until after we do code coverage, so that we 
    // can demonstrate how coverage helps to show what is and is not tested
    /*
    public void testPerson()
    {
        Person p1 = new Person();
        p1.setFirstName("first");
        p1.setLastName("last");
        
        Assert.assertEquals("first", p1.getFirstName());
        Assert.assertEquals("last", p1.getLastName());        
    }
    
    */
    
    public void testPersonEquals()
    {
        Person p1 = new Person("first1", "last1");
        Person p2 = new Person("first1", "last1");
        Person p3 = new Person("first1", null);
        Person p4 = new Person("first2", "last2");
        
        Assert.assertTrue(p1.equals(p2));
        Assert.assertFalse(p1.equals(p3));
        Assert.assertFalse(p1.equals(p4));
    }    
}