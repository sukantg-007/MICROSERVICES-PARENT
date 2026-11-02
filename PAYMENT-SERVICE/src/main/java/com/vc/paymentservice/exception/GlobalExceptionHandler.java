package com.vc.paymentservice.exception;

import com.vc.common.dto.ErrorResponse;
import jakarta.persistence.ElementCollection;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePaymentNotFoundException(
            PaymentNotFoundException e,
            HttpServletRequest request
            ) {
        return sendingErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception e,
            HttpServletRequest request
            ) {
        return sendingErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ErrorResponse> sendingErrorResponse(HttpStatus status, String error, String path){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.value())
                .error(error)
                .message(status.getReasonPhrase())
                .path(path)
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }
}
