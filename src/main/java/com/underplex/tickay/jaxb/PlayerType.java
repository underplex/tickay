//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.21 at 12:18:56 PM EST 
//


package com.underplex.tickay.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlayerType.

 * Enum values representing the seating of a player.
 * <code>PlayerType.APU</code> is the first player.<p>
 * <code>PlayerType.BARNEY</code> is the second player.<p>
 * <code>PlayerType.CARL</code> is the third player.<p>
 * <code>PlayerType.DUFFMAN</code> is the fourth player.<p>
 * <code>PlayerType.EDNA</code> is the fifth player.<p>
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PlayerType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="APU"/>
 *     &lt;enumeration value="BARNEY"/>
 *     &lt;enumeration value="CARL"/>
 *     &lt;enumeration value="DUFFMAN"/>
 *     &lt;enumeration value="EDNA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 * @author Brandon Irvine
 */
@XmlType(name = "PlayerType")
@XmlEnum
public enum PlayerType {

    APU,
    BARNEY,
    CARL,
    DUFFMAN,
    EDNA;

    public String value() {
        return name();
    }

    public static PlayerType fromValue(String v) {
        return valueOf(v);
    }

}
