package dev.earl.order_owl.exception.custom_exception.order;

public class OrderListEmptyException extends RuntimeException{

    public OrderListEmptyException(String message){super(message);}

    public OrderListEmptyException(String message, Throwable cause){super(message, cause);}


}
