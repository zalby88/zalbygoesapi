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
	
	private MongoDbFactory mongoFactory;
	private MongoOperations mongoOps;

	@Override
	public List<Origami> callListService() {
		try {
			// Initialize the connection at the first call
			if (!isConnectionInitialized()) {
				initMongoDbCollection();
			} 
			
			return mongoOps.findAll(Origami.class);
			
		} catch (Exception e) {
			throw new ServiceClientErrorException(e.getMessage());
		}
	}
	
	private void initMongoDbCollection() {
		// Set server address   
		ServerAddress serverAddress = new ServerAddress("127.0.0.1", Integer.parseInt("27017"));
		
	    // Mongo Client
	    MongoClient mongoClient = new MongoClient(serverAddress); 
		
	    // Mongo DB Factory
	    mongoFactory =  new SimpleMongoDbFactory(mongoClient, MONGODB_DB_NAME);
	    mongoOps = new MongoTemplate(mongoFactory);
	}
	
	private boolean isConnectionInitialized() {
		return mongoFactory != null && mongoOps != null;
	}
	
}// end class
