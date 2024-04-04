package dev.earl.order_owl.exception;

import dev.earl.order_owl.exception.custom_exception.cart.*;
import dev.earl.order_owl.exception.custom_exception.customer.*;
import dev.earl.order_owl.exception.custom_exception.order.*;
import dev.earl.order_owl.exception.custom_exception.product.ProductAlreadyExistsException;
import dev.earl.order_owl.exception.custom_exception.product.ProductConstraintViolationException;
import dev.earl.order_owl.exception.custom_exception.product.ProductListEmptyException;
import dev.earl.order_owl.exception.custom_exception.product.ProductNotFoundException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentAlreadyExistsException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentConstraintViolationException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentListEmptyException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * TODO :
     *  Include a logger to log exceptions that occur at controller level as well?
     *
     */

    /**
     *  Customer Exception Handlers
     */

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorInfo> customerNotFoundExceptionHandler(CustomerNotFoundException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
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
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> constraintViolationExceptionHandler(ConstraintViolationException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                "Invalid parameter input",
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

    /**
     * Shipment Exception handlers
     */

    @ExceptionHandler(ShipmentConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> shipmentConstraintViolationExceptionHandler(ShipmentConstraintViolationException exception){

        StringBuilder errorMessages = new StringBuilder();
        exception.getErrorMessages().forEach(message -> errorMessages.append(message).append(", "));

        ErrorInfo errorInfo = new ErrorInfo(
                errorMessages.substring(0, errorMessages.length() - 2),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShipmentAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> shipmentAlreadyExistsExceptionHandler(ShipmentAlreadyExistsException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShipmentNotFoundException.class)
    public ResponseEntity<ErrorInfo> shipmentNotFoundExceptionHandler(ShipmentNotFoundException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShipmentListEmptyException.class)
    public ResponseEntity<ErrorInfo> shipmentListEmptyExceptionHandler(ShipmentListEmptyException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    /**
     *  Order Exception Handlers
     */


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorInfo> orderNotFoundExceptionHandler(OrderNotFoundException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> orderAlreadyExistsExceptionHandler(OrderAlreadyExistsException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderListEmptyException.class)
    public ResponseEntity<ErrorInfo> orderListEmptyExceptionHandler(OrderListEmptyException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(OrderCreateNotValidException.class)
    public ResponseEntity<?> orderCreationNotValidException(OrderCreateNotValidException exception){
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
    @ExceptionHandler(OrderUpdateNotValidException.class)
    public ResponseEntity<?> orderCreationNotValidException(OrderUpdateNotValidException exception){

        StringBuilder errorMessages = new StringBuilder();
        exception.getErrorMessages().forEach(message -> errorMessages.append(message).append(", "));

        ErrorInfo errorInfo = new ErrorInfo(
                errorMessages.substring(0, errorMessages.length() - 2),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    /**
     * Product Exception Handlers
     */

    @ExceptionHandler(ProductConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> productConstraintViolationExceptionHandler(ProductConstraintViolationException exception){

        StringBuilder errorMessages = new StringBuilder();
        exception.getErrorMessages().forEach(message -> errorMessages.append(message).append(", "));

        ErrorInfo errorInfo = new ErrorInfo(
                errorMessages.substring(0, errorMessages.length() - 2),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> productAlreadyExistsExceptionHandler(ProductAlreadyExistsException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorInfo> productNotFoundExceptionHandler(ProductNotFoundException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductListEmptyException.class)
    public ResponseEntity<ErrorInfo> productListEmptyExceptionHandler(ProductListEmptyException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    /**
     * Cart Exception Handlers
     */
    @ExceptionHandler(CartConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> cartConstraintViolationExceptionHandler(CartConstraintViolationException exception){

        StringBuilder errorMessages = new StringBuilder();
        exception.getErrorMessages().forEach(message -> errorMessages.append(message).append(", "));

        ErrorInfo errorInfo = new ErrorInfo(
                errorMessages.substring(0, errorMessages.length() - 2),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartAlreadyExistsException.class)
    public ResponseEntity<ErrorInfo> cartAlreadyExistsExceptionHandler(CartAlreadyExistsException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorInfo> cartNotFoundExceptionHandler(CartNotFoundException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartListEmptyException.class)
    public ResponseEntity<ErrorInfo> cartListEmptyExceptionHandler(CartListEmptyException exception){
        ErrorInfo errorInfo = new ErrorInfo(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartInvalidQuantityException.class)
    public ResponseEntity<ErrorInfo> cartInvalidQuantityExceptionHandler(CartInvalidQuantityException exception){ErrorInfo errorInfo = new ErrorInfo(
            exception.getMessage(),
            HttpStatus.BAD_REQUEST.value(),
            LocalDateTime.now()
    );
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }





}
