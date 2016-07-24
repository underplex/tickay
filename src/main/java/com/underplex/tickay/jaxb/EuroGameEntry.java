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
import com.underplex.tickay.jaxbinfo.EuroGameEntryInfo;


/**
 * <p>Java class for EuroGameEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EuroGameEntry">
 *   &lt;complexContent>
 *     &lt;extension base="{}GameEntry">
 *       &lt;sequence>
 *         &lt;element name="stationChoice" type="{}StationChoiceEntry" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="finalScoring" type="{}EuroFinalScoringEntry" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EuroGameEntry", propOrder = {
    "stationChoice",
    "finalScoring"
})
public class EuroGameEntry
    extends GameEntry implements InfoSource<EuroGameEntryInfo>
{

    protected List<StationChoiceEntry> stationChoice;
    @XmlElement(required = true)
    protected List<EuroFinalScoringEntry> finalScoring;

    @XmlTransient
    private EuroGameEntryInfo info;

    public EuroGameEntry(){
    	this.info = new EuroGameEntryInfo(this);
    }
 
    public EuroGameEntryInfo info() {
		return this.info;
	}
    
    /**
     * Gets the value of the stationChoice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stationChoice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStationChoice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StationChoiceEntry }
     * 
     * 
     */
    public List<StationChoiceEntry> getStationChoice() {
        if (stationChoice == null) {
            stationChoice = new ArrayList<StationChoiceEntry>();
        }
        return this.stationChoice;
    }

    /**
     * Gets the value of the finalScoring property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the finalScoring property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFinalScoring().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EuroFinalScoringEntry }
     * 
     * 
     */
    public List<EuroFinalScoringEntry> getFinalScoring() {
        if (finalScoring == null) {
            finalScoring = new ArrayList<EuroFinalScoringEntry>();
        }
        return this.finalScoring;
    }

}