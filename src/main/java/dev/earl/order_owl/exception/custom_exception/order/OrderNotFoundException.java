package dev.earl.order_owl.exception.custom_exception.order;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message){super(message);}
    public OrderNotFoundException(String message, Throwable cause){super(message, cause);}
}
