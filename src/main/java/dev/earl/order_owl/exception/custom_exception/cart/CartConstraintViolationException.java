package dev.earl.order_owl.exception.custom_exception.cart;

import java.util.Set;

public class CartConstraintViolationException extends RuntimeException{

    private final Set<String> errorMessages;

    public CartConstraintViolationException(String message, Set<String> errorMessages){
        super(message);
        this.errorMessages = errorMessages;
    }

    public Set<String> getErrorMessages(){
        return errorMessages;
    }
}
