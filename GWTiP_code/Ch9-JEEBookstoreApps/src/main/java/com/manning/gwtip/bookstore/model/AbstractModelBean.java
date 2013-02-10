package com.manning.gwtip.bookstore.model;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public abstract class AbstractModelBean implements Serializable{
    
    public boolean equals( Object o ){
        return EqualsBuilder.reflectionEquals(this, o);
    }
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this);
    }
    public String toString(){
        return ReflectionToStringBuilder.toString( this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
}
