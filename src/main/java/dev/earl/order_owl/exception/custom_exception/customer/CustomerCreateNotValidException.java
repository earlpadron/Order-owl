package dev.earl.order_owl.exception.custom_exception.customer;

import java.util.Set;




public class CustomerCreateNotValidException extends RuntimeException{

    private final Set<String> errorMessages;


    public CustomerCreateNotValidException(String message, Set<String> errorMessages) {
        super(message);
        this.errorMessages = errorMessages;
    }

    public Set<String> getErrorMessages(){
        return errorMessages;
    }
}
