//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.21 at 12:18:56 PM EST 
//


package com.underplex.tickay.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StrategyErrorEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StrategyErrorEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="strategyClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strategyError" type="{}StrategyErrorType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StrategyErrorEntry", propOrder = {
    "strategyClass",
    "strategyError"
})
public class StrategyErrorEntry {

    @XmlElement(required = true)
    protected String strategyClass;
    @XmlElement(required = true)
    protected StrategyErrorType strategyError;

    /**
     * Gets the value of the strategyClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrategyClass() {
        return strategyClass;
    }

    /**
     * Sets the value of the strategyClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrategyClass(String value) {
        this.strategyClass = value;
    }

    /**
     * Gets the value of the strategyError property.
     * 
     * @return
     *     possible object is
     *     {@link StrategyErrorType }
     *     
     */
    public StrategyErrorType getStrategyError() {
        return strategyError;
    }

    /**
     * Sets the value of the strategyError property.
     * 
     * @param value
     *     allowed object is
     *     {@link StrategyErrorType }
     *     
     */
    public void setStrategyError(StrategyErrorType value) {
        this.strategyError = value;
    }

}
