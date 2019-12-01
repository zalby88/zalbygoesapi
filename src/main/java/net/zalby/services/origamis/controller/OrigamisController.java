package net.zalby.services.origamis.controller;

import java.time.Clock;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.zalby.services.origamis.controller.cache.OrigamiCache;
import net.zalby.services.origamis.data.access.OrigamiServiceClient;
import net.zalby.services.origamis.data.access.impl.OrigamiMockedServiceClient;
import net.zalby.services.origamis.data.access.impl.mongo.OrigamiMongoDbClient;
import net.zalby.services.origamis.logic.GetOrigamiLogicManager;
import net.zalby.services.origamis.logic.impl.GetOrigamiLogicManagerImpl;
import net.zalby.services.origamis.model.Origami;

/**
 * 
 * Main REST Controller Class for the "Origami" Resources. Includes all of the
 * entry point methods. Sets up the logic managers, service clients and
 * transformers. Performs basic routing functions when receiving a request.
 *
 * @author Alberto Lazzarin
 *
 */
@RestController
@Api(value = "Origamis", description = "The Endpoints list for the Origamis learnt by me")
public class OrigamisController {

	private static final String DATABASE_TYPE_MONGO = "mongo";
	private static final long CACHE_TIME_TO_LIVE = 500L;

	private String dbType;

	/* Service Clients */
	private OrigamiServiceClient origamiServiceClient;

	/* Cache */
	private OrigamiCache origamiCache;

	/**
	 * Main Constructor: Initialises the API Service Layer and Logic
	 */
	public OrigamisController(@Value("${zalbygoesapi.db.type}") String dbType) {
		this.dbType = dbType;
		initServiceClient();
		initCache();
	}

	/**
	 * 
	 * GET Origamis - request entry point method
	 * 
	 * @return A list of Origami which will be converted into a JSON array
	 */

	/* ***********************************/
	/* SWAGGER Configuration Annotations */

	@ApiOperation(value = "Created Origami List", response = Origami.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Origamis successfully retrieved") })

	/* **************************************/
	/* SPRING MVC Configuration Annotations */

	@RequestMapping(value = "/origamis", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Origami> getOrigamis() {
		
		// Cache layer
		if (origamiCache.retrieveOrigamis() == null) {
			origamiCache.storeOrigami(this.retrieveOrigamis());
		}

		return origamiCache.retrieveOrigamis();
	}

	/**
	 * Handles the Request logic, based on the query parameters
	 * 
	 * @return The list of Origami
	 */
	private List<Origami> retrieveOrigamis() {

		GetOrigamiLogicManager getOrigamiManager = new GetOrigamiLogicManagerImpl(getOrigamiServiceClient());

		return getOrigamiManager.getOrigamiResources();
	}

	/**
	 * Service Clients Initialisation routine.
	 */
	private void initServiceClient() {
		/*
		 * Beans injection is currently hard-coded, in future it could be re-factored
		 * using Spring annotations
		 */
		if (DATABASE_TYPE_MONGO.equals(getDbType())) {
			setOrigamiServiceClient(new OrigamiMongoDbClient());
		} else {
			// Mocked (Default) Service Client
			setOrigamiServiceClient(new OrigamiMockedServiceClient());
		}
	}

	/**
	 * Cache Initialisation routine.
	 */
	private void initCache() {
		/*
		 * Beans injection is currently hard-coded, in future it could be re-factored
		 * using Spring annotations
		 */
		setOrigamiCache(OrigamiCache.createCache(Clock.systemUTC(), CACHE_TIME_TO_LIVE));
	}

	/* -------------------- */
	/* GETTERS and SETTERS */

	public OrigamiServiceClient getOrigamiServiceClient() {
		return origamiServiceClient;
	}

	public void setOrigamiServiceClient(OrigamiServiceClient origamiServiceClient) {
		this.origamiServiceClient = origamiServiceClient;
	}

	public String getDbType() {
		return dbType;
	}

	public OrigamiCache getOrigamiCache() {
		return origamiCache;
	}

	public void setOrigamiCache(OrigamiCache origamiCache) {
		this.origamiCache = origamiCache;
	}

}// end class
