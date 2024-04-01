package dev.earl.order_owl.exception.custom_exception.product;

import java.util.Set;

public class ProductConstraintViolationException extends RuntimeException{

    private final Set<String> errorMessages;

    public ProductConstraintViolationException(String message, Set<String> errorMessages){
        super(message);
        this.errorMessages = errorMessages;
    }

    public Set<String> getErrorMessages(){
        return errorMessages;
    }
}
