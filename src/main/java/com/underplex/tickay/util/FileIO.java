package com.underplex.tickay.util;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.underplex.tickay.jaxb.SimulationLog;

/**
 * Utility class for file input-output static methods.
 * @author Brandon Irvine
 *
 */
public class FileIO {

	private FileIO(){
		// don't instantiate
	}
	
	/**
	 * Converts <code>log</code> to an XML file and returns<code>String</code> representing local file location.
	 * <p>
	 * Returns empty <code>String</code> if a file is not actually produced.
	 * @param log	<code>SimulationLog</code> representing runs of the simulation
	 * @param directory	<code>String</code> for the directory the file should be created in
	 * @return		<code>String</code> for the file location 
	 */
	public static String outputFile( SimulationLog log, String directory){
		String rString;
		String fileLocation = "";
		fileLocation += directory;
		
		Calendar cal = Calendar.getInstance();
	   	String month = String.valueOf( cal.get(Calendar.MONTH) + 1 );
	   	String year = String.valueOf( cal.get(Calendar.YEAR) );
	   	String day = String.valueOf( cal.get(Calendar.DAY_OF_MONTH) );
	   	String dateString = year + "-" + month + "-" + day;
		Random r = new Random();
		String randomID = String.valueOf(r.nextInt(9999)); // attach up to 4 random digits to file name
		fileLocation += dateString + "_" + randomID;
		fileLocation += ".xml";
		
		JAXBContext jaxbContext = null;
		
		try {
			jaxbContext = JAXBContext.newInstance( SimulationLog.class );
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
			jaxbMarshaller.marshal( log, new File( fileLocation ) );
			// jaxbMarshaller.marshal( log, System.out );
			System.out.println("JAXB xml output succeeded.");
			rString = fileLocation;
		} catch ( JAXBException e ) {
			System.out.println("JAXB xml output failed because of error: " + e.getMessage());
			e.printStackTrace();
			rString = "";
		}
		
		return rString;
	}
	
	/**
	 * Returns <code>SimulationLog</code> created from <code>fileLocation</code> or <code>null</code> if import failed.
	 * @param fileLocation	<code>String</code> for local file location of XML file
	 * @return SimulationLog representing data from file
	 */
	public static SimulationLog inputFile( String fileLocation ){
		
		Object obj = null;
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance( SimulationLog.class );
			Unmarshaller u = jaxbContext.createUnmarshaller();
		    obj = u.unmarshal( new File( fileLocation ) );
		} catch ( JAXBException e ) {
			System.out.println("JAXB xml input failed because of error: " + e.getMessage());
			e.printStackTrace();
		}

		SimulationLog rLog = null;
		if ( obj instanceof SimulationLog ){
			rLog = (SimulationLog)obj;
		}
		
		return rLog;
	}
	
}
