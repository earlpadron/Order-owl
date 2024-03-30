package dev.earl.order_owl.exception.custom_exception.product;

public class ProductAlreadyExistsException extends RuntimeException{

    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
