package net.zalby.services.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.zalby.services.origamis.exceptions.ResourceNotFoundException;

/**
 * A configuration class with additional Exception handlers used to overwrite the default error message
 * from the application server
 * 
 * @author Alberto Lazzarin
 *
 */
@ControllerAdvice
public class ControllerHelperConfig {

    /**
     * Handles ResourceNotFoundException for the Web application controllers.
     * @param The Exception to handle
     * @return The HTTP Response Entity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ResourceNotFoundException e) {

    	ErrorMessage errMessage = new ErrorMessage();
    	errMessage.setMessage(e.getMessage());
    	
        return ResponseEntity
        		.status(HttpStatus.NOT_FOUND)
        		.body(errMessage);
    }
    
}//end class

/**
 * Utility class used to create a basic error message in JSON format
 * 
 * @author Alberto Lazzarin
 *
 */
class ErrorMessage {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}//end utility class