package net.zalby.services.origamis.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * API Model class for the Origami resource
 * 
 * @author Alberto Lazzarin
 *
 */
public class Origami {
	
	private String id;
	private String name;
	private String author;
	private String difficulty;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public boolean equals(Object object) {
		if (object != null) {
			return this.toString().equals(object.toString());
		}
		
		return false;
	}
	
	/**
	 *  the Object representation as a JSON-formatted string
	 */
	@Override
	public String toString() {
		ObjectMapper jsonObjMapper = new ObjectMapper();
		
	    try {
	        return jsonObjMapper.writeValueAsString(this);
	    } catch (final JsonProcessingException jsonProcExc) {
	        return String.valueOf(this);
	    }
	}
}//end class