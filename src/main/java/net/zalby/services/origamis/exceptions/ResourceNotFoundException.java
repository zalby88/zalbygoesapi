package net.zalby.services.origamis.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Exception to represents the 404 (Not Found) HTTP Response case
 * 
 * @author Alberto Lazzarin
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * Generated Serial UID
	 */
	private static final long serialVersionUID = 3001886063480304461L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}// end class
