package dev.earl.order_owl.exception.custom_exception.cart;

public class CartAlreadyExistsException extends RuntimeException{
    public CartAlreadyExistsException(String message){
        super(message);
    }
}
