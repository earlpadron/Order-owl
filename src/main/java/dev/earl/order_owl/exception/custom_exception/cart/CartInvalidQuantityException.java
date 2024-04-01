package dev.earl.order_owl.exception.custom_exception.cart;

public class CartInvalidQuantityException extends RuntimeException{

    public CartInvalidQuantityException(String message){
        super(message);
    }
}
