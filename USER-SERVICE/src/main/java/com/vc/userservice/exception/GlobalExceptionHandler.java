package com.vc.userservice.exception;

import com.vc.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            UserNotFoundException e,
            HttpServletRequest req) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(
            DuplicateEmailException e,
            HttpServletRequest req) {
        return buildErrorResponse(HttpStatus.CONFLICT, e.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception e,
            HttpServletRequest req) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), req.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message, String path) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .timestamp(LocalDateTime.now())
                .path(path)
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }
}
