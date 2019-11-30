package net.zalby.services.origamis.logic.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import net.zalby.services.origamis.data.access.OrigamiServiceClient;
import net.zalby.services.origamis.data.access.impl.OrigamiMockedServiceClient;
import net.zalby.services.origamis.logic.GetOrigamiLogicManager;
import net.zalby.services.origamis.logic.impl.GetOrigamiLogicManagerImpl;
import net.zalby.services.origamis.model.Origami;

/**
 * Test Suite class for the Logic Manager.
 * The Service clients and transformers are mocked since here ONLY the (orchestration) logic is tested
 * 
 * @author Alberto Lazzarin
 *
 */
public class GetOrigamiLogicManagerImplTest {

	private OrigamiServiceClient serviceClientToMock;
	
	private GetOrigamiLogicManager logicManagerToTest;
	
	private List<Origami> expectedResponse;
	
	/**
	 * 1 - Arrange
	 * @throws IOException 
	 */
	@Before
	public void init() throws IOException {
		
		// Define the Mocked Service Client behaviour when called
		serviceClientToMock = spy(new OrigamiMockedServiceClient());
		
		// Initialise the expected response
		initExpectedResponse();

		//Define the mocked behaviour
		doReturn(expectedResponse).when(serviceClientToMock).callListService();
		
		// Finally, initialise the Logic Manager with the mocked components
		logicManagerToTest = new GetOrigamiLogicManagerImpl(serviceClientToMock);
	}

	
	@Test
	public void getOrigamiResources_success() {
		/*
		 *  2 - Act
		 */
		List<Origami> responseFromManager = logicManagerToTest.getOrigamiResources();
		
		/*
		 *  3 - Assert
		 */
		assertEquals("The Collected list from the Logic Manager should be equal to the expected one", 
					expectedResponse,
					responseFromManager);
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
