
package dk.example.web.demoparser.microservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;



/**
 * @author mnb@kmd.dk
 *
 */
public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Handles all Exceptions not addressed by more specific
     * <code>@ExceptionHandler</code> methods. Creates a response with the
     * Exception detail in the response body as JSON and a HTTP status code of
     * 500, internal server error.
     * 
     * @param exception
     *            An Exception instance.
     * @return A ResponseEntity containing a the Exception attributes in the
     *         response body and a HTTP status code 500.
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleException(Exception exception) {
        logger.error("> handleException");
        logger.error("- Exception: ", exception);
        logger.error("< handleException");
        return new ResponseEntity<Exception>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
