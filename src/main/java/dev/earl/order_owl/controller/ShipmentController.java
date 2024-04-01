package dev.earl.order_owl.controller;

import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentAlreadyExistsException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentConstraintViolationException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentListEmptyException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentNotFoundException;
import dev.earl.order_owl.model.Shipment;
import dev.earl.order_owl.service.ShipmentService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "order_owl/v1/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @GetMapping(value = "/{shipment_id}")
    public ResponseEntity<Shipment> getShipment(@PathVariable("shipment_id") @Min(1) Integer id) throws ShipmentNotFoundException{
        Shipment shipment = shipmentService.getShipment(id);
        return new ResponseEntity<>(shipment, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() throws ShipmentListEmptyException {
        List<Shipment> shipmentList = shipmentService.getAllShipments();
        return new ResponseEntity<>(shipmentList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment newShipment) throws ShipmentConstraintViolationException, ShipmentAlreadyExistsException {
        Shipment createdShipment = shipmentService.createShipment(newShipment);
        return new ResponseEntity<>(createdShipment, HttpStatus.CREATED);
    }

    @PutMapping("/{shipment_id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable("shipment_id") @Min(1) Integer id,
                                                   @RequestBody Shipment updateShipment) throws ShipmentNotFoundException, ShipmentConstraintViolationException {
        Shipment updatedShipment = shipmentService.updateShipment(id, updateShipment);
        return new ResponseEntity<>(updateShipment, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{shipment_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShipment(@PathVariable("shipment_id") @Min(1) Integer id) throws ShipmentNotFoundException{
        shipmentService.deleteShipment(id);
    }

}
