package net.zalby.services.origamis.logic.impl;

import java.util.List;

import net.zalby.services.origamis.data.access.OrigamiServiceClient;
import net.zalby.services.origamis.logic.GetOrigamiLogicManager;
import net.zalby.services.origamis.model.Origami;

/**
 * 
 * The Standard implementation for the Origami Logic Manager
 *
 * @author Alberto Lazzarin
 *
 */
public class GetOrigamiLogicManagerImpl implements GetOrigamiLogicManager {

	/*
	 * Required Service Clients and Transformers
	 */
	private OrigamiServiceClient origamiServiceClient;

	/**
	 * Base constructor. The Service client is required in order to retrieve the
	 * origami from the Data Layer
	 * 
	 * @param undergroundServiceClient
	 * @param undergroundServiceTransformer
	 */
	public GetOrigamiLogicManagerImpl(OrigamiServiceClient origamiServiceClient) {

		this.origamiServiceClient = origamiServiceClient;
	}

	public List<Origami> getOrigamiResources() {
		return getOrigamiServiceClient().callListService();
	}

	public OrigamiServiceClient getOrigamiServiceClient() {
		return origamiServiceClient;
	}

	public void setOrigamiServiceClient(OrigamiServiceClient origamiServiceClient) {
		this.origamiServiceClient = origamiServiceClient;
	}

}// end class
