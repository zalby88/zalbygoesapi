package net.zalby.services.origamis.data.access.impl.mongo;

import java.util.List;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import net.zalby.services.origamis.data.access.OrigamiServiceClient;
import net.zalby.services.origamis.exceptions.ServiceClientErrorException;
import net.zalby.services.origamis.model.Origami;

/**
 * A MongoDB Client implementation for the Origami Data layer service client
 * 
 * @author Alberto Lazzarin
 *
 */
public class OrigamiMongoDbClient implements OrigamiServiceClient {

	private static final String MONGODB_DB_NAME = "zalbystuff";
	private static final String LOCALHOST_IP_ADDRESS = "127.0.0.1";
	private static final String DEFAULT_MONGODB_PORT = "27017";

	private MongoOperations mongoOps;

	@Override
	public List<Origami> callListService() {
		try {
			// Initialise the connection at the first call
			if (!isConnectionInitialised()) {
				initMongoDbConnection();
			}

			return retrieveAllOrigamiFromConnection();

		} catch (Exception e) {
			throw new ServiceClientErrorException(e.getMessage());
		}
	}

	/**
	 *  Initialises the MongoDB Client classes in order to perform the call
	 */
	protected void initMongoDbConnection() {
		// Set server address
		ServerAddress serverAddress = 
				new ServerAddress(LOCALHOST_IP_ADDRESS, Integer.parseInt(DEFAULT_MONGODB_PORT));

		// Mongo Client
		MongoClient mongoClient = new MongoClient(serverAddress);

		// Mongo DB Factory
		MongoDbFactory mongoFactory = new SimpleMongoDbFactory(mongoClient, MONGODB_DB_NAME);

		// Mongo DB Operations
		mongoOps = new MongoTemplate(mongoFactory);
	}

	/**
	 *  Performs a basic check of the Client class (the MongoOperations one)
	 */
	protected boolean isConnectionInitialised() {
		return this.getMongoOps() != null;
	}
	
	/**
	 *  Performs the actual call to MongoDB in order to retrieve all of the Origami
	 */
	protected List<Origami> retrieveAllOrigamiFromConnection() {
		return getMongoOps().findAll(Origami.class);
	}

	/*
	 * Getters and Setters
	 * 
	 */

	public MongoOperations getMongoOps() {
		return mongoOps;
	}

}// end class
