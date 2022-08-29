package com.jdpt93.cycle3.backend.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandlingConfig extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> getErrorResponse(HttpStatus status, WebRequest request, Object message) {
        Map<String, Object> errorAttributes = new DefaultErrorAttributes()
                .getErrorAttributes(request, ErrorAttributeOptions.defaults());
        errorAttributes.put("status", status.value());
        errorAttributes.put("error", status.getReasonPhrase());
        errorAttributes.put("message", message);
        errorAttributes.put("path", ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(errorAttributes, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> all(Exception exception, WebRequest request) {
        log.error(exception.toString(), exception);
        return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, request, exception.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> all(ResponseStatusException exception, WebRequest request) {
        log.error(exception.toString(), exception);
        return getErrorResponse(exception.getStatus(), request, exception.getReason());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(exception.toString(), exception);
        List<String> messages = exception.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList();
        return getErrorResponse(status, request,
                messages.size() == 0
                        ? null
                        : messages.size() == 1
                                ? messages.get(0)
                                : messages);
    }

}