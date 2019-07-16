package net.zalby.services.origamis.data.access.impl;

import java.util.ArrayList;
import java.util.List;

import net.zalby.services.origamis.data.access.OrigamiServiceClient;
import net.zalby.services.origamis.model.Origami;

/**
 * A mocked implementation for the Origami Data layer service client
 * 
 * @author Alberto Lazzarin
 *
 */
public class OrigamiMockedServiceClient implements OrigamiServiceClient {

	public List<Origami> callListService() {
		return generateMockedList();
	}
	
	
	private List<Origami> generateMockedList() {
		
		ArrayList<Origami> mockedList = new ArrayList<Origami>();
		
		Origami toAdd = new Origami();
		toAdd.setId("batman");
		toAdd.setName("Batman");
		toAdd.setAuthor("Ángel Morollón Guallar");
		toAdd.setDifficulty("Medium");
		mockedList.add(toAdd);
		
		toAdd = new Origami();
		toAdd.setId("rose");
		toAdd.setName("Rose");
		toAdd.setAuthor("Toshikazu Kawasaki");
		toAdd.setDifficulty("Hard");
		mockedList.add(toAdd);
		
		toAdd = new Origami();
		toAdd.setId("trexslim");
		toAdd.setName("Tyrannosaurus Rex (Slim Version)");
		toAdd.setAuthor("John Montroll");
		toAdd.setDifficulty("Medium");
		mockedList.add(toAdd);
		
		toAdd = new Origami();
		toAdd.setId("trexfat");
		toAdd.setName("Tyrannosaurus Rex (Fat Version)");
		toAdd.setAuthor("Yoshihide Momotani");
		toAdd.setDifficulty("Easy");
		mockedList.add(toAdd);
		
		return mockedList;
	} 
	
}// end class
