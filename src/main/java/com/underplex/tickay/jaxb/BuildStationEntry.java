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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.underplex.infowrap.InfoSource;
import com.underplex.tickay.jaxbinfo.BuildStationEntryInfo;


/**
 * <p>Java class for BuildStationEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BuildStationEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="player" type="{}PlayerType"/>
 *         &lt;element name="stationNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="stationLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="spentForStation" type="{}TrainType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BuildStationEntry", propOrder = {
    "player",
    "stationNumber",
    "stationLocation",
    "spentForStation"
})
public class BuildStationEntry implements InfoSource<BuildStationEntryInfo>{

    @XmlElement(required = true)
    protected PlayerType player;
    protected int stationNumber;
    @XmlElement(required = true)
    protected String stationLocation;
    protected List<TrainType> spentForStation;
    
    @XmlTransient
    private BuildStationEntryInfo info;

    public BuildStationEntry(){
    	this.info = new BuildStationEntryInfo(this);
    }
    
    public BuildStationEntryInfo info() {
		return this.info;
	}
    
    /**
     * Gets the value of the player property.
     * 
     * @return
     *     possible object is
     *     {@link PlayerType }
     *     
     */
    public PlayerType getPlayer() {
        return player;
    }

    /**
     * Sets the value of the player property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlayerType }
     *     
     */
    public void setPlayer(PlayerType value) {
        this.player = value;
    }

    /**
     * Gets the value of the stationNumber property.
     * 
     */
    public int getStationNumber() {
        return stationNumber;
    }

    /**
     * Sets the value of the stationNumber property.
     * 
     */
    public void setStationNumber(int value) {
        this.stationNumber = value;
    }

    /**
     * Gets the value of the stationLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStationLocation() {
        return stationLocation;
    }

    /**
     * Sets the value of the stationLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStationLocation(String value) {
        this.stationLocation = value;
    }

    /**
     * Gets the value of the spentForStation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the spentForStation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpentForStation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TrainType }
     * 
     * 
     */
    public List<TrainType> getSpentForStation() {
        if (spentForStation == null) {
            spentForStation = new ArrayList<TrainType>();
        }
        return this.spentForStation;
    }
}