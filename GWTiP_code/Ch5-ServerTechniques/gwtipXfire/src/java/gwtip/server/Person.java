

package gwtip.server;

import org.codehaus.xfire.aegis.type.java5.XmlElement;


public class Person {
    
    private String firstName;
    private String lastName;
    public Person() {
        super();
    }
    
    @XmlElement(minOccurs="1",nillable=false)
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @XmlElement(minOccurs="1",nillable=false)
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}
