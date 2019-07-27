package net.zalby.services.origamis.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Exception to represents the 500 (Internal Server Error) HTTP Response case
 * 
 * @author Alberto Lazzarin
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
public class ServiceClientErrorException extends RuntimeException {

	/**
	 * Generated Serial UID
	 */
	private static final long serialVersionUID = -3160421183488303774L;

	public ServiceClientErrorException(String message) {
		super(message);
	}
}// end class
