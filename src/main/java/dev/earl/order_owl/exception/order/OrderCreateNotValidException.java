package dev.earl.order_owl.exception.order;

import java.util.Set;

public class OrderCreateNotValidException extends RuntimeException{

    private final Set<String> errorMessages;

    public OrderCreateNotValidException(String message, Set<String> errorMessages){
        super(message);
        this.errorMessages = errorMessages;
    }

    public Set<String> getErrorMessages(){
        return errorMessages;
    }
}
