package dev.earl.order_owl.service.validator;

import dev.earl.order_owl.exception.custom_exception.customer.CustomerCreateNotValidException;
import dev.earl.order_owl.model.dto.CustomerDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomerValidator extends EntityValidator<CustomerDTO>{

      public void validate(CustomerDTO customerDto, String message){
          Set<String> errorMessages = super.validate(customerDto);
          if(errorMessages != null){
              throw new CustomerCreateNotValidException(message, errorMessages);
          }
      }






}
