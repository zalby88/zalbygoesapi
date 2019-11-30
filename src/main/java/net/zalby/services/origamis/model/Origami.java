package net.zalby.services.origamis.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

import org.springframework.data.annotation.Id;

/**
 * API Model class for the Origami resource
 * 
 * @author Alberto Lazzarin
 *
 */
public class Origami {

	@Id
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
		if (object == this) {
			return true;
		}

		if (!(object instanceof Origami)) {
			return false;
		}

		Origami orgm = (Origami) object;
		return Objects.equals(orgm.getId(), this.id) 
				&& Objects.equals(orgm.getName(), this.name)
				&& Objects.equals(orgm.getAuthor(), this.author)
				&& Objects.equals(orgm.getDifficulty(), this.difficulty);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.author, this.difficulty);
	}

	/**
	 * @return the Object representation as a JSON-formatted string
	 *
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
}// end class