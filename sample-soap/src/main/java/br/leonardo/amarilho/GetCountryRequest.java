package br.leonardo.amarilho;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD) 
@XmlType(name = "", propOrder = { "name" }) 
@XmlRootElement(name = "getCountryRequest") 
public class GetCountryRequest { 
 
    @XmlElement(required = true) 
    protected String name; 
 
    public String getName() { 
        return name; 
    } 
 
    public void setName(String value) { 
        this.name = value; 
    } 
 
}