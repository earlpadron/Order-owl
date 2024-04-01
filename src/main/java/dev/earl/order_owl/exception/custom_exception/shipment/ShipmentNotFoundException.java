package dev.earl.order_owl.exception.custom_exception.shipment;

public class ShipmentNotFoundException extends RuntimeException{

    public ShipmentNotFoundException(String message){
        super(message);
    }
}
