package br.com.fatecararas.f290_dsm_tp2_suggestionbox_ct.config.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler({ ObjectNotFoundException.class, EmptyResultDataAccessException.class })
    public ResponseEntity<ApiError> notFoundException(ObjectNotFoundException exception,
            HttpServletRequest request) {
        var error = new ApiError(System.currentTimeMillis(),
                404, exception.getMessage(),
                "Resource not Found",
                request.getRequestURI(), new HashMap<>());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> illegalArgumentException(IllegalArgumentException exception,
                                                             HttpServletRequest request) {
        var error = new ApiError(
                System.currentTimeMillis(),
                400,
                exception.getMessage(),
                "Argumentos inválidos.",
                request.getRequestURI(),
                new HashMap<>()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        var error = new ApiError(
                System.currentTimeMillis(),
                400, ex.getMessage(),
                "Something wrong with the inputs",
                request.getRequestURI(), errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
