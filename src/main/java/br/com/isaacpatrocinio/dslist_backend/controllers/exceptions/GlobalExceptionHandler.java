package br.com.isaacpatrocinio.dslist_backend.controllers.exceptions;

import br.com.isaacpatrocinio.dslist_backend.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(
            ResourceNotFoundException e,
            HttpServletRequest request
    ) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        StandardError errorObj = new StandardError(
                Instant.now(),
                notFoundStatus.value(),
                request.getRequestURI(),
                "Resource not found",
                e.getMessage()
        );

        return ResponseEntity.status(notFoundStatus).body(errorObj);
    }
}
