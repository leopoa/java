package br.leonardo.amarilho;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD) 
@XmlType(name = "", propOrder = { "country" }) 
@XmlRootElement(name = "getCountryResponse") 
public class GetCountryResponse { 
 
    @XmlElement(required = true) 
    protected Country country; 
 
    public Country getCountry() { 
        return country; 
    } 
 
    public void setCountry(Country value) { 
        this.country = value; 
    } 
 
}
