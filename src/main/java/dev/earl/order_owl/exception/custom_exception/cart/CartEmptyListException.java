package dev.earl.order_owl.exception.custom_exception.cart;

public class CartEmptyListException extends RuntimeException{

    public CartEmptyListException(String message){
        super(message);
    }
}
