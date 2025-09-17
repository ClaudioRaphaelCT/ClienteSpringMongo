package com.basic.postgres.client.exceptions;

import com.basic.postgres.client.dto.api.GlobalExceptionResponse;
import com.basic.postgres.client.utils.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private final MessageService message;
    private final HttpServletRequest request;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalExceptionResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String instanceId = UUID.randomUUID().toString();
        Map<String, List<String>> validationErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            validationErrors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        });
        String baseUrl = ServletUriComponentsBuilder.fromContextPath(request).build().toUriString();
        String errorTypeUrl = baseUrl + "/swagger-ui/index.html#/";
        GlobalExceptionResponse response = new GlobalExceptionResponse(
                errorTypeUrl,
                message.getMessage("api.exception.validation.title"),
                HttpStatus.BAD_REQUEST.value(),
                message.getMessage("api.exception.validation.detail"),
                instanceId,
                validationErrors
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalExceptionResponse> handlerNotFoundException(NotFoundException ex) {
        String instanceId = UUID.randomUUID().toString();
        String baseUrl = ServletUriComponentsBuilder.fromContextPath(request).build().toUriString();
        String errorTypeUrl = baseUrl + "/swagger-ui/index.html#/";
        GlobalExceptionResponse response = new GlobalExceptionResponse(
                errorTypeUrl,
                message.getMessage("api.exception.notfound.title"),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                instanceId,
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
