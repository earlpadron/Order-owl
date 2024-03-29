package dev.earl.order_owl.exception;

import dev.earl.order_owl.exception.custom_exception.customer.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorInfo> customerNotFoundExceptionHandler(CustomerNotFoundException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> customerAlreadyExistsExceptionHandler(CustomerAlreadyExistsException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerListEmptyException.class)
    public ResponseEntity<ErrorInfo> customerListEmptyExceptionHandler(CustomerListEmptyException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> constraintViolationExceptionHandler(ConstraintViolationException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                "Invalid input",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerCreateNotValidException.class)
    public ResponseEntity<?> customerCreationNotValidException(CustomerCreateNotValidException exception){
        StringBuilder errorMessages = new StringBuilder();
        exception.getErrorMessages()
                .forEach(message -> errorMessages.append(message).append(", "));

        ErrorInfo errorInfo = new ErrorInfo(
                errorMessages.substring(0, errorMessages.length() - 2),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomerUpdateNotValidException.class)
    public ResponseEntity<?> customerCreationNotValidException(CustomerUpdateNotValidException exception){

        StringBuilder errorMessages = new StringBuilder();
        exception.getErrorMessages().forEach(message -> errorMessages.append(message).append(", "));

        ErrorInfo errorInfo = new ErrorInfo(
                errorMessages.substring(0, errorMessages.length() - 2),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

}
