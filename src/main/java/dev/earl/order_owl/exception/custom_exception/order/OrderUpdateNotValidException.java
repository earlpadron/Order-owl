package dev.earl.order_owl.exception.custom_exception.order;

import java.util.Set;

public class OrderUpdateNotValidException extends RuntimeException{

    private final Set<String> errorMessages;

    public OrderUpdateNotValidException(String message, Set<String> errorMessages){
        super(message);
        this.errorMessages = errorMessages;
    }

    public Set<String> getErrorMessages(){
        return errorMessages;
    }
}
