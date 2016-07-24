/**
 * Provides classes that are converted to XML or converted from XML using JAXB tools.
 * <p>
 * These classes aim to provide a complete record of a game of Ticket to Ride (America) or Ticket to Ride Europe; "complete" here means the entire game, down to every
 * move of a card from one place to another, should be replicable by reading the XML element that contains the game record (EuroGameEntry or AmericaGameEntry).
 * <p>
 * Many of these classes might have been automaticaly generated using an Eclipse plug-in that took a schema in XSD format and generated the appropriate JAXB classes.
 * <p>
 * Since further versions and expansions of Ticket to Ride might be implemented in the future, here's a quick summary of 
 * the steps used to create the classes
 * in this package:
 * First, SimulationLogging.xsd and TickayGameBase.xsd were written to create many of the basic elements of Ticket to Ride and 
 * a log that would hold multiple simulations of the some version of the game.
 * <p>
 * Next, schemas were created that extended SimulationLogging.xsd and TickayGameBase.xsd for the European and American versions of the game. As of
 * February 2016, the latest versions of the game-specific schemas were EuroTickayData_v9.xsd and AmericaTickayData_v1.xsd.
 * <p>
 * Next, these last two were used to automatically generate (using JAXB tools) the <code>-Entry</code> and <code>-Log</code> classes of this package.
 * <p>
 * Finally, some of the classes were altered so that they could provide immutable versions of themselves. This is because the simulator uses these classes
 * as a record of the game to show to user-defined strategies.
 * @author Brandon Irvine
 *
 */
package com.underplex.tickay.jaxb;