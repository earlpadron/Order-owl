package dev.earl.order_owl.exception.custom_exception.order;

public class OrderAlreadyExistsException extends RuntimeException{

    public OrderAlreadyExistsException(String message){super(message);}
    public OrderAlreadyExistsException(String message, Throwable cause){super(message, cause);}
}
