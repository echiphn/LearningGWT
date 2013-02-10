package com.manning.gwtip.testme.client.model;

import com.google.gwt.user.client.rpc.IsSerializable;


public class Person implements IsSerializable {
    private String firstName;
    private String lastName;

    // remember, with GWT IsSerializable you must have a no args const
    // (so if you override the def, you have to include one)
    public Person() {
    }

    public Person(String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return "Person: " + firstName + " " + lastName;
    }

    public boolean equals(Object o) {
        boolean returnValue = false;

        if (this == o) {
            returnValue = true;
        }

        // cannot use getClass in GWT!
        // technically, this violates equals symmetry (subclass will be "equals), but it is 
        if ((o != null) && (o instanceof Person)) {
            Person oPerson = (Person) o;

            if ((this.firstName.equals(oPerson.firstName)) &&
                    (this.lastName.equals(oPerson.lastName))) {
                returnValue = true;
            }
        }

        return returnValue;
    }

    public int hashCode() {
        int hash = 11;

        if (this.firstName != null) {
            hash += this.firstName.hashCode();
            hash *= 22;
        }

        if (this.lastName != null) {
            hash += this.lastName.hashCode();
            hash *= 33;
        }

        return hash;
    }
}
