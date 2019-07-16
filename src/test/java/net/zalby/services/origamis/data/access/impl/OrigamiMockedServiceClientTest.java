package net.zalby.services.origamis.data.access.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.zalby.services.origamis.data.access.impl.OrigamiMockedServiceClient;
import net.zalby.services.origamis.model.Origami;

/**
 * Test Suite class for the Transport For London Underground Service HTTP Client.
 * The actual HTTP call is mocked in order to avoid external calls.
 * 
 * @author Alberto Lazzarin
 *
 */
public class OrigamiMockedServiceClientTest {
	
	private OrigamiMockedServiceClient serviceClientToTest;
	private List<Origami> expectedResponse;
	
	/**
	 * 1 - Arrange
	 */
	@Before
	public void initMocks() {
		// Initialising the Expected Response
		initExpectedResponse();
		
		serviceClientToTest = spy(new OrigamiMockedServiceClient());
		
		// The mock will be triggered when the actual GET call is made
		doReturn(expectedResponse).when(serviceClientToTest).callListService();
	}

	
	@Test
	public void callService_success() {
		/*
		 *  2 - Act 
		 */ 
		List<Origami> responseList = serviceClientToTest.callListService();
		
		/*
		 *  3 - Assert 
		 */ 
		assertEquals("The Response should match the expected one", expectedResponse, responseList);
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
