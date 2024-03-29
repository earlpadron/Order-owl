package dev.earl.order_owl.exception.custom_exception.customer;

import java.util.Set;

public class CustomerUpdateNotValidException extends RuntimeException{

    private final Set<String> errorMessages;

    public CustomerUpdateNotValidException(Set<String> errorMessages, String message) {
        super(message);
        this.errorMessages = errorMessages;
    }

    public Set<String> getErrorMessages(){
        return errorMessages;
    }
}
