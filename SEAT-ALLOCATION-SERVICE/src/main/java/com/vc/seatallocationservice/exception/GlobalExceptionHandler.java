package com.vc.seatallocationservice.exception;

import com.vc.common.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            SeatNotFoundException e,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(SeatAlreadyAllocatedException.class)
    public ResponseEntity<ErrorResponse> handleConflict(
            SeatAlreadyAllocatedException e,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception e,
            HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String error, String path) {
       ErrorResponse errorResponse = ErrorResponse.builder()
               .status(status.value())
               .message(status.getReasonPhrase())
               .error(error)
               .timestamp(LocalDateTime.now())
               .path(path)
               .build();
       return ResponseEntity.status(status).body(errorResponse);
    }
}
