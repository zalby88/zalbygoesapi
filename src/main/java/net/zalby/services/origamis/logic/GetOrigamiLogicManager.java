package net.zalby.services.origamis.logic;

import java.util.List;

import net.zalby.services.origamis.model.Origami;

/**
 * 
 * The Business Logic Manager abstraction used by the Origami Controller. 
 *
 * @author Alberto Lazzarin
 *
 */
public interface GetOrigamiLogicManager {

	/**
	 * Applies the Business Logic and Orchestration in order to retrieve the Origami for the Specified Requirements
	 * 
	 * @return The list of Origami for the specified requirements
	 */
	public List<Origami> getOrigamiResources();
	
}// end class
