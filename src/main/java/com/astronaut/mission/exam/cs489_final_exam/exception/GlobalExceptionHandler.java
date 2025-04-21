package com.astronaut.mission.exam.cs489_final_exam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AstronautNotFoundException.class, SatelliteNotFoundException.class})
    public ResponseEntity<ApiError> handleNotFound(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ApiError(
                        Instant.now(),
                        404,
                        "Not Found",
                        ex.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(SatelliteUpdateNotAllowedException.class)
    public ResponseEntity<ApiError> handleUpdateError(Exception ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ApiError(
                        Instant.now(),
                        403,
                        "Forbidden",
                        ex.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class, IllegalStateException.class})
    public ResponseEntity<ApiError> handleEnumConversionError(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String param = ex.getName();
        String message = "Invalid value for '" + param + "': " + ex.getValue();
        ApiError error = new ApiError(Instant.now(), 400, "Bad Request", message, request.getRequestURI());
        return ResponseEntity.badRequest().body(error);
    }
}
