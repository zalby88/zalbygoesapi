package net.zalby.services.origamis.data.access.impl.mongo;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.zalby.services.origamis.data.access.impl.mongo.OrigamiMongoDbClient;
import net.zalby.services.origamis.exceptions.ServiceClientErrorException;
import net.zalby.services.origamis.model.Origami;

/**
 * Test Suite class for the MongoDB (Data Layer) Origami Service class.
 * External calls are mocked
 * 
 * @author Alberto Lazzarin
 *
 */
public class OrigamiMongoDbClientTest {
	
	private OrigamiMongoDbClient serviceClientToTest;
	private List<Origami> expectedResponse;
	private Exception expectedException;
	
	/**
	 * 1 - Arrange
	 */
	@Before
	public void initMocks() {
		// Initialising the Expected Exception
		 expectedException = new ServiceClientErrorException("Mocked Exception");
		
		// Initialising the Expected Response
		initExpectedResponse();
		
		serviceClientToTest = spy(new OrigamiMongoDbClient());
		
		// The mock will be triggered when trying to get the Operations class 
		// (responsible to perform the external calls)
		doReturn(true)
			.when(serviceClientToTest).isConnectionInitialised();
	}

	
	@Test
	public void callService_success() {
		/*
		 *  2 - Act 
		 */ 
		
		// The mock will be triggered when the Operations class performs the GET call
		doReturn(expectedResponse)
			.when(serviceClientToTest).retrieveAllOrigamiFromConnection();
		
		List<Origami> responseList = serviceClientToTest.callListService();
		
		/*
		 *  3 - Assert 
		 */ 
		assertEquals("The Response should match the expected one", expectedResponse, responseList);
	}
	
	@Test
	public void callService_failure() {
		/*
		 *  2 - Act 
		 */ 
		
		// The mock will be triggered when the Operations class performs the GET call
		doThrow(new RuntimeException(expectedException.getMessage()))
			.when(serviceClientToTest).retrieveAllOrigamiFromConnection();
		
		try {
			serviceClientToTest.callListService();
			
			//should never reach this point
			fail("An exception should have been raised before this point");
		
		} catch (Exception exceptionToEvaluate) {
			/*
			 *  3 - Assert 
			 */ 
			assertEquals("The thrown exception class must be the expected one", 
					expectedException.getClass(), exceptionToEvaluate.getClass());
			
			assertEquals("The thrown exception message must be the expected one",
					expectedException.getMessage(), exceptionToEvaluate.getMessage());
		} 
	}
	
	/* -----------------------
	 * Private Utility Methods 
	 * -----------------------
	 */
	
	/**
	 * Generates the Expected Response object which will be compared to the one returned by the service under test
	 */
	private void initExpectedResponse() {
		expectedResponse = new ArrayList<Origami>();
		
		Origami toAdd = new Origami();
		toAdd.setId("batman");
		toAdd.setName("Batman");
		toAdd.setAuthor("Ángel Morollón Guallar");
		toAdd.setDifficulty("Medium");
		expectedResponse.add(toAdd);
		
		toAdd = new Origami();
		toAdd.setId("rose");
		toAdd.setName("Rose");
		toAdd.setAuthor("Toshikazu Kawasaki");
		toAdd.setDifficulty("Hard");
		expectedResponse.add(toAdd);
		
		toAdd = new Origami();
		toAdd.setId("trexslim");
		toAdd.setName("Tyrannosaurus Rex (Slim Version)");
		toAdd.setAuthor("John Montroll");
		toAdd.setDifficulty("Medium");
		expectedResponse.add(toAdd);
		
		toAdd = new Origami();
		toAdd.setId("trexfat");
		toAdd.setName("Tyrannosaurus Rex (Fat Version)");
		toAdd.setAuthor("Yoshihide Momotani");
		toAdd.setDifficulty("Easy");
		expectedResponse.add(toAdd);
	}

}//end class
