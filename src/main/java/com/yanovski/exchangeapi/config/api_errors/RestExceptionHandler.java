package com.yanovski.exchangeapi.config.api_errors;

import com.yanovski.exchangeapi.config.api_errors.exceptions.ApiEntityNotFoundException;
import com.yanovski.exchangeapi.config.api_errors.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

/**
 * Controller Advice providing generic error handling for some common web exceptions.
 * The result is an ApiError class with structured error description.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String label = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .label(label)
                .message(ex.getMessage())
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        return buildResponseEntity(ApiError.builder()
                .status(UNSUPPORTED_MEDIA_TYPE)
                .label(builder.substring(0, builder.length() - 2))
                .message(ex.getMessage())
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        String message = "Field Errors: { " +
                ex.getBindingResult().getFieldErrors().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")) +
                " } Global Errors {" +
                ex.getBindingResult().getGlobalErrors().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")) +
                " }";

        ApiError apiError = ApiError.builder()
                .status(BAD_REQUEST)
                .label("Validation error")
                .message(message)
                .build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ApiEntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            ApiEntityNotFoundException ex) {
        ApiError apiError = ApiError.builder()
                .status(NOT_FOUND)
                .label("Entity not found")
                .message(ex.getMessage())
                .build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            NumberFormatException ex) {
        ApiError apiError = ApiError.builder()
                .status(BAD_REQUEST)
                .label("Cannot convert to number")
                .message(ex.getMessage())
                .build();
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        log.error("{} to {}", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());
        return buildResponseEntity(ApiError.builder()
                .status(BAD_REQUEST)
                .label("Malformed JSON request")
                .message(ex.getMessage())
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(BAD_REQUEST)
                .label(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL()))
                .message(ex.getMessage())
                .build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                  WebRequest request) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(ApiError.builder()
                    .status(CONFLICT)
                    .label("Database error")
                    .message(ex.getCause().getMessage())
                    .build());
        }
        return buildResponseEntity(ApiError.builder()
                .status(INTERNAL_SERVER_ERROR)
                .label("Unknown error")
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                                                                      WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(BAD_REQUEST)
                .label(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                        ex.getName(),
                        ex.getValue(),
                        ex.getRequiredType() == null ? "Unknown" : ex.getRequiredType().getSimpleName()))
                .message(ex.getMessage())
                .build();
        return buildResponseEntity(apiError);
    }


    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}
