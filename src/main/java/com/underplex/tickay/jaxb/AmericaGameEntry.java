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
import com.underplex.tickay.jaxbinfo.AmericaGameEntryInfo;


/**
 *Java class for AmericaGameEntry complex type.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AmericaGameEntry", propOrder = {
    "finalScoring"
})
public class AmericaGameEntry
    extends GameEntry implements InfoSource<AmericaGameEntryInfo>
{
	@XmlTransient
    private AmericaGameEntryInfo info;
    
    @XmlElement(required = true)
    protected List<FinalScoringEntry> finalScoring;
    
    public AmericaGameEntry(){
    	this.info = new AmericaGameEntryInfo(this);
    }
    
    public AmericaGameEntryInfo info() {
		return this.info;
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
    public List<FinalScoringEntry> getFinalScoring() {
        if (finalScoring == null) {
            finalScoring = new ArrayList<FinalScoringEntry>();
        }
        return this.finalScoring;
    }

 }