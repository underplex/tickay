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
 * <p>Java class for StrategyErrorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StrategyErrorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FIRST_DISCARD_CHOICE"/>
 *     &lt;enumeration value="REGULAR_TURN_CHOICE"/>
 *     &lt;enumeration value="SECOND_CARD_CHOICE"/>
 *     &lt;enumeration value="TICKET_CHOICE"/>
 *     &lt;enumeration value="TUNNEL_CHOICE"/>
 *     &lt;enumeration value="STATION_COVERAGE_CHOICE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StrategyErrorType")
@XmlEnum
public enum StrategyErrorType {

    FIRST_DISCARD_CHOICE,
    REGULAR_TURN_CHOICE,
    SECOND_CARD_CHOICE,
    TICKET_CHOICE,
    TUNNEL_CHOICE,
    STATION_COVERAGE_CHOICE;

    public String value() {
        return name();
    }

    public static StrategyErrorType fromValue(String v) {
        return valueOf(v);
    }

}
