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
 * <p>Java class for PlayType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PlayType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TAKE_TRAINS"/>
 *     &lt;enumeration value="DRAW_TICKETS"/>
 *     &lt;enumeration value="CLAIM_ROUTE"/>
 *     &lt;enumeration value="BUILD_STATION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PlayType")
@XmlEnum
public enum PlayType {

    TAKE_TRAINS,
    DRAW_TICKETS,
    CLAIM_ROUTE,
    BUILD_STATION;

    public String value() {
        return name();
    }

    public static PlayType fromValue(String v) {
        return valueOf(v);
    }

}
