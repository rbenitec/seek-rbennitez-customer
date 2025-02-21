package com.seek.rbenitez.customer.exception;

import com.seek.rbenitez.customer.controller.dto.ErrorDto;
import jakarta.servlet.ServletException;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorDto error = ErrorDto.builder()
                .code("RBEN-400")
                .message(Objects.requireNonNull(ex.getFieldError()).getField()
                        .concat(" ")
                        .concat(Objects.requireNonNull(ex.getFieldError().getDefaultMessage())))
                .timestamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnexpectedTypeException.class)
    public ResponseEntity<ErrorDto> businessExceptionHandler(UnexpectedTypeException ex) {
        ErrorDto error = ErrorDto.builder()
                .code("RBEN-422")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.UNPROCESSABLE_ENTITY)
                .build();
        log.error("[businessExceptionHandler] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorDto> businessExceptionHandler(BusinessException ex) {
        ErrorDto error = ErrorDto.builder().code(ex.getCode()).message(ex.getMessage()).build();
        log.error("[businessExceptionHandler] - {}", error);
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorDto> handleDatabaseException(DataAccessException ex) {
        ErrorDto error = ErrorDto.builder()
                .code("RBEN-503")
                .message("Error de conexión con la base de datos. Por favor, intenta más tarde.")
                .timestamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.SERVICE_UNAVAILABLE)
                .build();
        log.error("[handleDatabaseException] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception ex) {
        ErrorDto error = ErrorDto.builder()
                .code("RBEN-500")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        log.error("[handleGeneralException] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> handleAuthenticationException(AuthenticationException ex) {
        ErrorDto error = ErrorDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .httpStatus(ex.getHttpStatus())
                .build();
        log.error("[handleAuthenticationException] - {}", error);
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorDto> internalAuthenticationServiceException(InternalAuthenticationServiceException ex) {
        ErrorDto error = ErrorDto.builder()
                .code("RBEN-401")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
        log.error("[handleAuthenticationException] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDto> iOException(IOException ex) {
        ErrorDto error = ErrorDto.builder()
                .code("RBEN-401")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
        log.error("[handleAuthenticationException] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<ErrorDto> servletException(ServletException ex) {
        ErrorDto error = ErrorDto.builder()
                .code("RBEN-401")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
        log.error("[handleAuthenticationException] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDto> usernameNotFoundException(UsernameNotFoundException ex) {
        ErrorDto error = ErrorDto.builder()
                .code("RBEN-401")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .build();
        log.error("[handleAuthenticationException] - {}", error);
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
