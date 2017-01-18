//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.21 at 12:18:56 PM EST 
//

package com.underplex.tickay.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SimulationLog complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SimulationLog">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateRun" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="specClassName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="expansions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="error" type="{}SpecErrorEntry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SimulationLog", propOrder = {
    "dateRun",
    "specClassName",
    "expansions",
    "error"
})
@XmlSeeAlso({
    EuroSimulationLog.class,
    AmericaSimulationLog.class
})
public class SimulationLog {

    @XmlElement(required = true)
    protected String dateRun;
    @XmlElement(required = true)
    protected String specClassName;
    @XmlElement(required = true)
    protected String expansions;
    protected List<SpecErrorEntry> error;

    /**
     * Gets the value of the dateRun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateRun() {
        return dateRun;
    }

    /**
     * Sets the value of the dateRun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateRun(String value) {
        this.dateRun = value;
    }

    /**
     * Gets the value of the specClassName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecClassName() {
        return specClassName;
    }

    /**
     * Sets the value of the specClassName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecClassName(String value) {
        this.specClassName = value;
    }

    /**
     * Gets the value of the expansions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpansions() {
        return expansions;
    }

    /**
     * Sets the value of the expansions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpansions(String value) {
        this.expansions = value;
    }

    /**
     * Gets the value of the error property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the error property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SpecErrorEntry }
     * 
     * 
     */
    public List<SpecErrorEntry> getError() {
        if (error == null) {
            error = new ArrayList<SpecErrorEntry>();
        }
        return this.error;
    }

}
