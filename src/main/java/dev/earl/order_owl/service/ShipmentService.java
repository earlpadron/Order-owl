package dev.earl.order_owl.service;

import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentAlreadyExistsException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentConstraintViolationException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentListEmptyException;
import dev.earl.order_owl.exception.custom_exception.shipment.ShipmentNotFoundException;
import dev.earl.order_owl.model.Shipment;
import dev.earl.order_owl.repository.ShipmentRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final Environment environment;
    private final Validator validator;

    public ShipmentService(ShipmentRepository shipmentRepository, Environment environment, Validator validator) {
        this.shipmentRepository = shipmentRepository;
        this.environment = environment;
        this.validator = validator;
    }

    public Shipment getShipment(Integer id) throws ShipmentNotFoundException{
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new ShipmentNotFoundException(environment.getProperty("service.shipment.not.found")));
    }

    public List<Shipment> getAllShipments() throws ShipmentListEmptyException{
        List<Shipment> shipmentList = shipmentRepository.findAll();
        if(shipmentList.isEmpty()) throw new ShipmentListEmptyException(environment.getProperty("service.shipment.list.empty"));
        return shipmentList;
    }

    public Shipment createShipment(Shipment newShipment) throws ShipmentAlreadyExistsException, ShipmentConstraintViolationException{
        Set<ConstraintViolation<Shipment>> violations = validator.validate(newShipment);
        if(!violations.isEmpty()){
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ShipmentConstraintViolationException(
                    environment.getProperty("service.shipment.invalid.create"),
                    errorMessages);
        }

        if(shipmentRepository.existsById(newShipment.getShipmentId())){
            throw new ShipmentAlreadyExistsException(environment.getProperty("service.shipment.already.exists"));
        }

        return shipmentRepository.save(newShipment);
    }

    public Shipment updateShipment(Integer id, Shipment updatedShipment) throws ShipmentNotFoundException, ShipmentConstraintViolationException{
        Set<ConstraintViolation<Shipment>> violations = validator.validate(updatedShipment);
        if(!violations.isEmpty()){
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ShipmentConstraintViolationException(environment.getProperty("service.shipment.invalid.update"),
                    errorMessages);
        }

        Shipment shipmentToUpdate = shipmentRepository.findById(updatedShipment.getShipmentId())
                .orElseThrow(() -> new ShipmentNotFoundException(environment.getProperty("service.shipment.not.found")));

        //update the shipment if none of the exceptions are thrown above
        shipmentToUpdate.setShipmentDate(updatedShipment.getShipmentDate());
        shipmentToUpdate.setStatus(updatedShipment.getStatus());
        shipmentToUpdate.setAddress(updatedShipment.getAddress());
        shipmentToUpdate.setCustomer(updatedShipment.getCustomer());

        return shipmentRepository.save(shipmentToUpdate);
    }

    public void deleteShipment(Integer id) throws ShipmentNotFoundException{
        if(!shipmentRepository.existsById(id)){
            throw new ShipmentNotFoundException(environment.getProperty("service.shipment.not.found"));
        }
        shipmentRepository.deleteById(id);
    }
}
