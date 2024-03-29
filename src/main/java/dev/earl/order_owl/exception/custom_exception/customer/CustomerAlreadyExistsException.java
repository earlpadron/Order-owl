package dev.earl.order_owl.exception.custom_exception.customer;

public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException(String message){
        super(message);
    }

    public CustomerAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }
}
