package net.zalby.services.origamis.logic.impl;

import java.util.ArrayList;
import java.util.List;

import net.zalby.services.origamis.data.access.OrigamiServiceClient;
import net.zalby.services.origamis.logic.OrigamiLogicManager;
import net.zalby.services.origamis.model.Origami;

/**
 * 
 * The Standard implementation for the Origami Logic Manager
 *
 * @author Alberto Lazzarin
 *
 */
public class OrigamiLogicManagerDefaultImpl implements OrigamiLogicManager {
	


	/*
	 * Required Service Clients and Transformers
	 */
	private OrigamiServiceClient origamiServiceClient;
	
	/**
	 * Base constructor. 
	 * The Clients and Transformers are required in order to apply the business logic on them
	 * 
	 * @param undergroundServiceClient
	 * @param undergroundServiceTransformer
	 */
	public OrigamiLogicManagerDefaultImpl(
			OrigamiServiceClient origamiServiceClient) {
		
		this.origamiServiceClient = origamiServiceClient;
	}
	
	
	/**
	 * Applies the Business Logic and Orchestration
	 * 
	 * @return The list of Origami
	 */
	public List<Origami> getOrigamiResources() {
		List<Origami> responseList = new ArrayList<Origami>();
		
		// ----------------------------------------------
		// Adding the Origami from the data access client

		responseList
				.addAll(getOrigamiServiceClient().callListService());
		
		return responseList;
	}


	public OrigamiServiceClient getOrigamiServiceClient() {
		return origamiServiceClient;
	}


	public void setOrigamiServiceClient(OrigamiServiceClient origamiServiceClient) {
		this.origamiServiceClient = origamiServiceClient;
	}
	
}// end class
