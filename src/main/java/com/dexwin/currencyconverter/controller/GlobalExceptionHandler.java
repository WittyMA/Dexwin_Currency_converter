// Import necessary classes and annotations
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GlobalExceptionHandler is a centralized class for handling exceptions across
 * multiple controllers in a Spring application. It uses the @ControllerAdvice
 * annotation to apply its methods globally to all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles IllegalArgumentException exceptions thrown by any controller method.
     * When such an exception occurs, this method is invoked, and it returns a
     * ResponseEntity with a 400 Bad Request status and the exception message in
     * the response body.
     *
     * @param ex the IllegalArgumentException thrown
     * @return a ResponseEntity containing the error message and HTTP status 400
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Return a 400 Bad Request response with the exception message as the body
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Handles RuntimeException exceptions thrown by any controller method.
     * When such an exception occurs, this method is invoked, and it returns a
     * ResponseEntity with a 500 Internal Server Error status and a generic error
     * message in the response body.
     *
     * @param ex the RuntimeException thrown
     * @return a ResponseEntity containing a generic error message and HTTP status 500
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Return a 500 Internal Server Error response with a generic error message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
    }
}
