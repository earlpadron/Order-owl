package dev.earl.order_owl.controller;

import dev.earl.order_owl.exception.custom_exception.customer.*;
import dev.earl.order_owl.model.dto.CustomerDTO;
import dev.earl.order_owl.service.CustomerService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "order_owl/v1/")
@Validated
public class CustomerController {


    /**
     * TODO :
     *  check - create and send customized exceptions for more detailed responses to client
     *  check - provide validation on path variables and received requestbody
     *  check - RestControllerAdvice
     *  - expand on customized queries
     *  in the repository
     */
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "customers/{customer_id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable(value = "customer_id") @Min(1) Integer customerId) throws CustomerNotFoundException {
        CustomerDTO customerDTO = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping(value = "customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws CustomerListEmptyException {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    //create

    /**
     * NOTICE
     * the incoming customerDTO is not annotated with @Valid since it is being validated by a custom validator instead
     */
    @PostMapping(value = "customers")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerAlreadyExistsException, CustomerCreateNotValidException {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    //update
    @PutMapping(value = "customers/{customer_id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable(value = "customer_id") @Min(value = 1) Integer id,
                                                      @RequestBody CustomerDTO customerDTO) throws CustomerNotFoundException, CustomerUpdateNotValidException {
       CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
       return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);
    }

    //delete
    @DeleteMapping(value = "customers/{customer_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable(value = "customer_id") @Min(value = 1) Integer id) throws CustomerNotFoundException{
        customerService.deleteCustomer(id);
    }



}
