package dev.earl.order_owl.exception.custom_exception.product;


public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }
}
