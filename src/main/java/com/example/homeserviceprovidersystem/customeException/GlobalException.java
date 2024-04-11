package com.example.homeserviceprovidersystem.customeException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull MethodArgumentNotValidException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status,
            @NonNull WebRequest request) {
        List<String> errorList = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("messages", errorList);
        body.put("status", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<String> handleCustomRuntimeException(CustomRuntimeException customRuntimeException) {
        return new ResponseEntity<>(customRuntimeException.getMessage(), HttpStatus.FOUND);
    }

    @ExceptionHandler(CustomResourceNotFoundException.class)
    public ResponseEntity<String> CustomResourceNotFoundException(CustomResourceNotFoundException customResourceNotFoundException) {
        return new ResponseEntity<>(customResourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
