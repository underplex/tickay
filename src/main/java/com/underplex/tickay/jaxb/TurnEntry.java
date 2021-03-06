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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.underplex.infowrap.InfoSource;
import com.underplex.infowrap.MyInfoSource;
import com.underplex.tickay.jaxbinfo.TurnEntryInfo;
import com.underplex.tickay.jaxbinfo.TurnEntryInfoMe;


/**
 * <p>Java class for TurnEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TurnEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="turn" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="player" type="{}PlayerType"/>
 *         &lt;element name="playType" type="{}PlayType"/>
 *         &lt;choice>
 *           &lt;element name="claimRoute" type="{}ClaimRouteEntry"/>
 *           &lt;element name="drawTickets" type="{}DrawTicketsEntry"/>
 *           &lt;element name="takeTrains" type="{}TakeTrainsEntry"/>
 *           &lt;element name="buildStation" type="{}BuildStationEntry"/>
 *         &lt;/choice>
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
@XmlType(name = "TurnEntry", propOrder = {
    "turn",
    "player",
    "playType",
    "claimRoute",
    "drawTickets",
    "takeTrains",
    "buildStation",
    "error"
})
public class TurnEntry implements InfoSource<TurnEntryInfo>, MyInfoSource<TurnEntryInfoMe> {

    protected int turn;
    @XmlElement(required = true)
    protected PlayerType player;
    @XmlElement(required = true)
    protected PlayType playType;
    protected ClaimRouteEntry claimRoute;
    protected DrawTicketsEntry drawTickets;
    protected TakeTrainsEntry takeTrains;
    protected BuildStationEntry buildStation;
    protected StrategyErrorEntry error;

    @XmlTransient
    private TurnEntryInfo info;
    @XmlTransient
    private TurnEntryInfoMe myInfo;
    
    public TurnEntry(){
    	this.info = new TurnEntryInfo(this);
    	this.myInfo = new TurnEntryInfoMe(this);
    }
    
    public TurnEntryInfo info() {
		return this.info;
	}

    public TurnEntryInfoMe myInfo() {
		return this.myInfo;
	}
    
    /**
     * Gets the value of the turn property.
     * 
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Sets the value of the turn property.
     * 
     */
    public void setTurn(int value) {
        this.turn = value;
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
     * Gets the value of the playType property.
     * 
     * @return
     *     possible object is
     *     {@link PlayType }
     *     
     */
    public PlayType getPlayType() {
        return playType;
    }

    /**
     * Sets the value of the playType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlayType }
     *     
     */
    public void setPlayType(PlayType value) {
        this.playType = value;
    }

    /**
     * Gets the value of the claimRoute property.
     * 
     * @return
     *     possible object is
     *     {@link ClaimRouteEntry }
     *     
     */
    public ClaimRouteEntry getClaimRoute() {
        return claimRoute;
    }

    /**
     * Sets the value of the claimRoute property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClaimRouteEntry }
     *     
     */
    public void setClaimRoute(ClaimRouteEntry value) {
        this.claimRoute = value;
    }

    /**
     * Gets the value of the drawTickets property.
     * 
     * @return
     *     possible object is
     *     {@link DrawTicketsEntry }
     *     
     */
    public DrawTicketsEntry getDrawTickets() {
        return drawTickets;
    }

    /**
     * Sets the value of the drawTickets property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrawTicketsEntry }
     *     
     */
    public void setDrawTickets(DrawTicketsEntry value) {
        this.drawTickets = value;
    }

    /**
     * Gets the value of the takeTrains property.
     * 
     * @return
     *     possible object is
     *     {@link TakeTrainsEntry }
     *     
     */
    public TakeTrainsEntry getTakeTrains() {
        return takeTrains;
    }

    /**
     * Sets the value of the takeTrains property.
     * 
     * @param value
     *     allowed object is
     *     {@link TakeTrainsEntry }
     *     
     */
    public void setTakeTrains(TakeTrainsEntry value) {
        this.takeTrains = value;
    }

    /**
     * Gets the value of the buildStation property.
     * 
     * @return
     *     possible object is
     *     {@link BuildStationEntry }
     *     
     */
    public BuildStationEntry getBuildStation() {
        return buildStation;
    }

    /**
     * Sets the value of the buildStation property.
     * 
     * @param value
     *     allowed object is
     *     {@link BuildStationEntry }
     *     
     */
    public void setBuildStation(BuildStationEntry value) {
        this.buildStation = value;
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
