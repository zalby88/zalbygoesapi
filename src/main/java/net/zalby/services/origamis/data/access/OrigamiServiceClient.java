package net.zalby.services.origamis.data.access;

import java.util.List;

import net.zalby.services.origamis.model.Origami;

/**
 * Abstraction of a Origami Service or Data Client
 * 
 * @author Alberto Lazzarin
 *
 */
public interface OrigamiServiceClient {
	
	/**
	 * 
	 * Calls the Service and returns a list of origami from the data service
	 * 
	 * 
	 */
	List<Origami> callListService();
	
}//end interface
