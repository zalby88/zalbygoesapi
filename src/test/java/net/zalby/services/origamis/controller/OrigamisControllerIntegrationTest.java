package net.zalby.services.origamis.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.zalby.services.origamis.controller.OrigamisController;
import net.zalby.services.origamis.data.access.impl.OrigamiMockedServiceClient;
import net.zalby.services.origamis.model.Origami;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Integration Test Suite for the Origamis Controller.
 * This one covers the whole API implementation with the exception of the external services calls, 
 * which are mocked in order to keep the unit tests free from external dependencies.
 * 
 * @author Alberto Lazzarin
 *
 */
@WebAppConfiguration
public class OrigamisControllerIntegrationTest {
	
	private MockMvc mockMvc;
	private ObjectMapper jsonMapper;
	
	private OrigamiMockedServiceClient serviceClientToMock;
	private List<Origami> expectedResponse;
	
	
	/**
	 * 1 - Arrange
	 * @throws IOException 
	 */
	@Before
	public void init() throws IOException {
		// Initialising the JSON Parser
		jsonMapper = new ObjectMapper();
		
		// Initialising the Expected Response
		initExpectedResponse();
		

		// Defining the Mocked Service Client behaviour when called
		serviceClientToMock = spy(new OrigamiMockedServiceClient());
		
		doReturn(expectedResponse).when(serviceClientToMock).callListService();
		
		// Creating the Controller
		OrigamisController controllerToBeTested = new OrigamisController();
		
		// Injecting the Mocked Service Client (This prevents that the actual HTTP call will performed)
		controllerToBeTested.setOrigamiServiceClient(serviceClientToMock);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(controllerToBeTested).build();
	}
	
	@Test
	public void getOrigamis_200() throws Exception {
		/*
		 *  2 - Act 
		 */ 
		ResultActions callResult = mockMvc
				.perform(
						get("/origamis"));
		
		/*
		 *  3 - Assert 
		 */ 
		MvcResult resultAfterCall = callResult
			.andExpect(status().isOk())
			.andReturn();
		
		// This will also make sure that the response is in an actual JSON format
		String responseBody =  resultAfterCall.getResponse().getContentAsString();
		List<Origami> parsedResponse = jsonMapper.readValue(responseBody, new TypeReference<List<Origami>>(){});
		
		assertEquals("The Response Body from the Service must match the expected one", expectedResponse, parsedResponse);
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
