package dev.earl.order_owl.exception.custom_exception.cart;

public class CartListEmptyException extends RuntimeException{

    public CartListEmptyException(String message){
        super(message);
    }
}
