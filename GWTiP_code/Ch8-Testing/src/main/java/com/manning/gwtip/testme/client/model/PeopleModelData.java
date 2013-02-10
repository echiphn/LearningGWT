package com.manning.gwtip.testme.client.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;


public class PeopleModelData implements IsSerializable, ModelData
    {
    // data holder object for People
    private List people;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public PeopleModelData()
    {
        this.people = new ArrayList();
    }
    
    public List getPeople() {
        return people;
    }
    
    public void setPeople(List l) {
        List oldPeople = new ArrayList();
        oldPeople.addAll(this.people);
        this.people = l;
        this.pcs.firePropertyChange("people", oldPeople, this.people);
    }

    public void addPerson(Person p) {
        List oldPeople = new ArrayList();
        oldPeople.addAll(this.people);
        this.people.add(p);
        this.pcs.firePropertyChange("people", oldPeople, this.people);
    }
    
    public void removePerson(Person p)
    {        
        if (this.people.contains(p))
        {
            List oldPeople = new ArrayList();
            oldPeople.addAll(this.people);
            this.people.remove(p);
            this.pcs.firePropertyChange("people", oldPeople, this.people);
        }
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        this.pcs.removePropertyChangeListener(listener);
    }   
}
