package dev.earl.order_owl.exception.custom_exception.shipment;

public class ShipmentAlreadyExistsException extends RuntimeException{

    public ShipmentAlreadyExistsException(String message){
        super(message);
    }
}
