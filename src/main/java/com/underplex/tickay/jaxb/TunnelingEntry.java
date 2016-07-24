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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.MyInfoSource;
import com.underplex.tickay.jaxbinfo.TunnelingEntryInfo;
import com.underplex.tickay.jaxbinfo.TunnelingEntryInfoMe;


/**
 * <p>Java class for TunnelingEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TunnelingEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="additionalTurnedUp" type="{}DeckDrawEntry" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="additionalRequired" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="canPay" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="doesPay" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="spentForAdditional" type="{}TrainType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="error" type="{}StrategyErrorEntry" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TunnelingEntry", propOrder = {
    "additionalTurnedUp",
    "additionalRequired",
    "canPay",
    "doesPay",
    "spentForAdditional",
    "error"
})
public class TunnelingEntry implements InfoSource<TunnelingEntryInfo>, MyInfoSource<TunnelingEntryInfoMe>{

    protected List<DeckDrawEntry> additionalTurnedUp;
    protected Integer additionalRequired;
    protected Boolean canPay;
    protected Boolean doesPay;
    protected List<TrainType> spentForAdditional;
    protected StrategyErrorEntry error;

    @XmlTransient
    private TunnelingEntryInfoMe myInfo;
    @XmlTransient
    private TunnelingEntryInfo info;

    public TunnelingEntry(){
    	this.info = new TunnelingEntryInfo(this);
    }
    
    public TunnelingEntryInfo info() {
		return this.info;
	}
    
    public TunnelingEntryInfoMe myInfo() {
		return this.myInfo;
	}

    /**
     * Gets the value of the additionalTurnedUp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalTurnedUp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalTurnedUp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeckDrawEntry }
     * 
     * 
     */
    public List<DeckDrawEntry> getAdditionalTurnedUp() {
        if (additionalTurnedUp == null) {
            additionalTurnedUp = new ArrayList<DeckDrawEntry>();
        }
        return this.additionalTurnedUp;
    }

    /**
     * Gets the value of the additionalRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAdditionalRequired() {
        return additionalRequired;
    }

    /**
     * Sets the value of the additionalRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAdditionalRequired(Integer value) {
        this.additionalRequired = value;
    }

    /**
     * Gets the value of the canPay property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCanPay() {
        return canPay;
    }

    /**
     * Sets the value of the canPay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCanPay(Boolean value) {
        this.canPay = value;
    }

    /**
     * Gets the value of the doesPay property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDoesPay() {
        return doesPay;
    }

    /**
     * Sets the value of the doesPay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDoesPay(Boolean value) {
        this.doesPay = value;
    }

    /**
     * Gets the value of the spentForAdditional property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spentForAdditional property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpentForAdditional().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrainType }
     * 
     * 
     */
    public List<TrainType> getSpentForAdditional() {
        if (spentForAdditional == null) {
            spentForAdditional = new ArrayList<TrainType>();
        }
        return this.spentForAdditional;
    }

    /**
     * Gets the value of the error property.
     * 
     * @return
     *     possible object is
     *     {@link StrategyErrorEntry }
     *     
     */
    public StrategyErrorEntry getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     * 
     * @param value
     *     allowed object is
     *     {@link StrategyErrorEntry }
     *     
     */
    public void setError(StrategyErrorEntry value) {
        this.error = value;
    }

}