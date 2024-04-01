package dev.earl.order_owl.exception.custom_exception.product;

public class ProductListEmptyException extends RuntimeException{

    public ProductListEmptyException(String message){
        super(message);
    }
}
