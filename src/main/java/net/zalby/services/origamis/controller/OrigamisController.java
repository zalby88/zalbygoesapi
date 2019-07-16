package net.zalby.services.origamis.controller;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.zalby.services.origamis.data.access.OrigamiServiceClient;
import net.zalby.services.origamis.data.access.impl.OrigamiMockedServiceClient;
import net.zalby.services.origamis.logic.OrigamiLogicManager;
import net.zalby.services.origamis.logic.impl.OrigamiLogicManagerDefaultImpl;
import net.zalby.services.origamis.model.Origami;

/**
 * 
 * Main REST Controller Class for the "Origami" Resources.
 * Includes all of the entry point methods. Sets up the logic managers, service clients and transformers.
 * Performs basic routing functions when receiving a request.
 *
 * @author Alberto Lazzarin
 *
 */
@RestController
@Api(value = "Origamis", description = "The Endpoints list for the Origamis learnt by me")
public class OrigamisController {

	/* Service Clients */
	private OrigamiServiceClient origamiServiceClient;

	/**
	 *  Main Constructor: Initialises the API Service Layer and Logic
	 */
	public OrigamisController() {
		init();
	}
	
	/**
	 * 
	 * GET Origamis - request entry point method
	 * 
	 * @return A list of Origami which will be converted into a JSON array
	 */
	
	/* ***********************************/
	/* SWAGGER Configuration Annotations */
	
	@ApiOperation(
			value = "Created Origami List", 
			response = Origami.class, 
			responseContainer = "List")

	@ApiResponses(value = 
			{ 
					@ApiResponse(code = 200, message = "Origamis successfully retrieved")
			})

	/* **************************************/
	/* SPRING MVC Configuration Annotations */
	
	@RequestMapping(
			value = "/origamis",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<Origami> getOrigamis() {

		return retrieveOrigamis();
	}

	/**
	 * Handles the Request logic, based on the query parameters
	 * 
	 * @return The list of Origami
	 */
	private List<Origami> retrieveOrigamis() {

		OrigamiLogicManager origamiManager = 
				new OrigamiLogicManagerDefaultImpl(getOrigamiServiceClient());

		return origamiManager.getOrigamiResources();
	}

	/**
	 * Controller Initialisation routine.
	 */
	private void init() {
		/* Beans injection is currently hard-coded, in future it could be re-factored using Spring annotations */
		setOrigamiServiceClient(new OrigamiMockedServiceClient());
	}


	
	/* --------------------*/
	/* GETTERS and SETTERS */
	
	public OrigamiServiceClient getOrigamiServiceClient() {
		return origamiServiceClient;
	}

	public void setOrigamiServiceClient(OrigamiServiceClient origamiServiceClient) {
		this.origamiServiceClient = origamiServiceClient;
	}

}// end class
