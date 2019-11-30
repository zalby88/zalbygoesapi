package net.zalby.services.origamis.model;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import net.zalby.services.origamis.model.Origami;

/**
 * Test Suite class for Origami object base methods
 * 
 * @author Alberto Lazzarin
 *
 */
public class OrigamiTest {

	private Origami firstOrigami;
	private String firstOrigamiExpectedText;

	private Origami equivalentOrigami;
	private Origami differentOrigami;

	/**
	 * 1 - Arrange
	 * 
	 * @throws IOException
	 */
	@Before
	public void init() throws IOException {
		initOrigamis();
	}

	@Test
	public void equals_null() {
		/*
		 * 1 - Arrange
		 */
		Object orgm1 = firstOrigami;

		/*
		 * 2 - Act
		 */
		boolean result = orgm1.equals(null);

		/*
		 * 3 - Assert
		 */
		assertEquals("An Origami must not be equal to null (with no Exception thrown)", false, result);
	}

	@Test
	public void equals_reflexivity() {
		/*
		 * 1 - Arrange
		 */
		Object orgm1 = firstOrigami;

		/*
		 * 2 - Act
		 */
		boolean result = orgm1.equals(orgm1);

		/*
		 * 3 - Assert
		 */
		assertEquals("An Origami must be equal to itself", true, result);
	}

	@Test
	public void equals_simmetricity() {
		/*
		 * 1 - Arrange
		 */
		Object orgm1 = firstOrigami;
		Object orgm2 = equivalentOrigami;

		/*
		 * 2 - Act
		 */
		boolean assumption = orgm1.equals(orgm2);
		boolean result = orgm2.equals(orgm1);

		/*
		 * 3 - Assert
		 */
		assertEquals("An Origami must be equal to another one with the same fields.", true, assumption);

		assertEquals("If an Origami is equal to another one, then the other way round must be true (simmetricity)",
				true, result);

	}

	@Test
	public void equals_different_fields() {
		/*
		 * 1 - Arrange
		 */
		Object orgm1 = firstOrigami;
		Object orgm2 = differentOrigami;

		/*
		 * 2 - Act
		 */
		boolean assumption = orgm1.equals(orgm2);
		boolean result = orgm2.equals(orgm1);

		/*
		 * 3 - Assert
		 */
		assertEquals("An Origami must be diffeerent to another one with different fields.", false, assumption);

		assertEquals("If an Origami is different to another one, then the other way round must be true (simmetricity)",
				false, result);

	}

	@Test
	public void equals_different_object() {
		/*
		 * 1 - Arrange
		 */
		Object orgm1 = firstOrigami;
		Object notOrgm2 = "Fake Origami";

		/*
		 * 2 - Act
		 */
		boolean result = orgm1.equals(notOrgm2);

		/*
		 * 3 - Assert
		 */
		assertEquals("An Origami must be diffeerent to an object of a different class", false, result);
	}

	@Test
	public void hashCode_for_equal_origamis() {
		/*
		 * 1 - Arrange
		 */
		Object orgm1 = firstOrigami;
		Object orgm2 = equivalentOrigami;

		/*
		 * 2 - Act
		 */
		int hash1 = orgm1.hashCode();
		int hash2 = orgm2.hashCode();

		/*
		 * 3 - Assert
		 */
		assertEquals("Equivalent Origamis must have the same hashCode", hash1, hash2);
	}

	@Test
	public void toString_success() {
		/*
		 * 1 - Arrange
		 */
		Object orgm1 = firstOrigami;

		/*
		 * 2 - Act
		 */
		String orgmText = orgm1.toString();

		/*
		 * 3 - Assert
		 */
		assertEquals("The String representation of an Origami must be equal to the expected one",
				firstOrigamiExpectedText, orgmText);
	}

	/*
	 * ----------------------- Private Utility Methods -----------------------
	 */

	/**
	 * Generates the Origamis under test
	 */
	private void initOrigamis() {
		firstOrigami = new Origami();
		firstOrigami.setId("batman");
		firstOrigami.setName("Batman");
		firstOrigami.setAuthor("Ángel Morollón Guallar");
		firstOrigami.setDifficulty("Medium");

		firstOrigamiExpectedText = "{\"id\":\"batman\",\"name\":\"Batman\",\"author\":\"Ángel Morollón Guallar\",\"difficulty\":\"Medium\"}";

		equivalentOrigami = new Origami();
		equivalentOrigami.setId("batman");
		equivalentOrigami.setName("Batman");
		equivalentOrigami.setAuthor("Ángel Morollón Guallar");
		equivalentOrigami.setDifficulty("Medium");

		differentOrigami = new Origami();
		differentOrigami.setId("trexfat");
		differentOrigami.setName("Tyrannosaurus Rex (Fat Version)");
		differentOrigami.setAuthor("Yoshihide Momotani");
		differentOrigami.setDifficulty("Easy");
	}
}// end class
