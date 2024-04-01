package dev.earl.order_owl.exception.custom_exception.cart;

public class CartNotFoundException extends RuntimeException{

    public CartNotFoundException(String message){
        super(message);
    }
}
