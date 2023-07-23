package com.dispel4py.rest.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(javax.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(javax.persistence.EntityNotFoundException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
    }


    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExists(EntityExistsException ex) {
        ApiError apiError = new ApiError(CONFLICT, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(javax.persistence.EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExists(javax.persistence.EntityExistsException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, ex.getMessage(), ex));
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorized(UnauthorizedException ex) {
        return buildResponseEntity(new ApiError(UNAUTHORIZED, ex.getMessage(), ex));
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Object> handleAuth(AuthenticationException ex) {
        ApiError apiError = new ApiError(UNAUTHORIZED, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
