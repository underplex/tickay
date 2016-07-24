//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.21 at 12:18:56 PM EST 
//


package com.underplex.tickay.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.MyInfoSource;
import com.underplex.tickay.jaxbinfo.DeckDrawEntryInfo;
import com.underplex.tickay.jaxbinfo.DeckDrawEntryInfoMe;


/**
 * <p>Java class for DeckDrawEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeckDrawEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cardToDraw" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="trainType" type="{}TrainType" minOccurs="0"/>
 *         &lt;element name="reshuffle" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeckDrawEntry", propOrder = {
    "cardToDraw",
    "trainType",
    "reshuffle"
})
public class DeckDrawEntry implements InfoSource<DeckDrawEntryInfo>, MyInfoSource<DeckDrawEntryInfoMe>{

    protected boolean cardToDraw;
    protected TrainType trainType;
    protected Boolean reshuffle;

    @XmlTransient
    private DeckDrawEntryInfo info;
    @XmlTransient
    private DeckDrawEntryInfoMe myInfo;

    public DeckDrawEntry(){
    	this.info = new DeckDrawEntryInfo(this);
    	this.myInfo = new DeckDrawEntryInfoMe(this);
    }
    
    public DeckDrawEntryInfo info() {
		return this.info;
	}

    public DeckDrawEntryInfoMe myInfo() {
		return this.myInfo;
	}
    
    /**
     * Gets the value of the cardToDraw property.
     * 
     */
    public boolean isCardToDraw() {
        return cardToDraw;
    }

    /**
     * Sets the value of the cardToDraw property.
     * 
     */
    public void setCardToDraw(boolean value) {
        this.cardToDraw = value;
    }

    /**
     * Gets the value of the trainType property.
     * 
     * @return
     *     possible object is
     *     {@link TrainType }
     *     
     */
    public TrainType getTrainType() {
        return trainType;
    }

    /**
     * Sets the value of the trainType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrainType }
     *     
     */
    public void setTrainType(TrainType value) {
        this.trainType = value;
    }

    /**
     * Gets the value of the reshuffle property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReshuffle() {
        return reshuffle;
    }

    /**
     * Sets the value of the reshuffle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReshuffle(Boolean value) {
        this.reshuffle = value;
    }

}