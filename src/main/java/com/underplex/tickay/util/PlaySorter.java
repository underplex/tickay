package com.underplex.tickay.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.underplex.tickay.info.PlayInfo;
import com.underplex.tickay.jaxb.PlayType;

/**
 * Utility class that sorts play with static methods.
 * @author Brandon Irvine
 *
 */
public class PlaySorter {

	private PlaySorter(){
		// don't instantiate
	}
	
	public static Map<PlayType, Set<PlayInfo>> sortByType( Set<? extends PlayInfo> options){
		
		Map<PlayType, Set<PlayInfo>> rMap = new HashMap<>();
		
		for ( PlayType pt : PlayType.values() )
			rMap.put( pt, new HashSet<PlayInfo>() );
		
		for ( PlayInfo pi : options )
			rMap.get( pi.getPlayType()).add( pi );
		
		return rMap;
	}
	
	
}
