package dev.earl.order_owl.exception.custom_exception.shipment;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;

public class ShipmentConstraintViolationException extends RuntimeException {

    private final Set<String> errorMessages;
    public ShipmentConstraintViolationException(String message,Set<String> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }

    public Set<String> getErrorMessages(){
        return errorMessages;
    }


}
