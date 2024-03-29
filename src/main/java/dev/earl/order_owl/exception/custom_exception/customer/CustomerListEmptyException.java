package dev.earl.order_owl.exception.custom_exception.customer;

public class CustomerListEmptyException extends RuntimeException{

    public CustomerListEmptyException(String message){
        super(message);
    }

    public CustomerListEmptyException(String message, Throwable cause){
        super(message, cause);
    }
}
