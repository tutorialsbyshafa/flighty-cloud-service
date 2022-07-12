package com.tutorials.flightyauthms.handler;

import com.tutorials.flightyauthms.model.ErrorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handle(Exception ex) {
        if (ex instanceof MethodArgumentNotValidException exception) {
            var message = Objects.requireNonNull(exception.getBindingResult().getFieldError())
                    .getDefaultMessage();

            return new ResponseEntity<>(
                    ErrorResponseModel.builder().message(message).code(9001).build(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                ErrorResponseModel.builder().message(ex.getMessage()).code(9999).build(),
                HttpStatus.BAD_REQUEST);
    }
}
