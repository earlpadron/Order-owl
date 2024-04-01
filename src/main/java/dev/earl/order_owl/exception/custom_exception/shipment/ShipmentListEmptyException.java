package dev.earl.order_owl.exception.custom_exception.shipment;

public class ShipmentListEmptyException extends RuntimeException{

    public ShipmentListEmptyException(String message){
        super(message);
    }
}
