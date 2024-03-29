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
public class CustomerValidator{

    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public void validate(CustomerDTO objectToValidate, String message){

        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(objectToValidate);
         if(!violations.isEmpty()){
             Set<String> errorMessages =  violations.stream()
                     .map(ConstraintViolation::getMessage)
                     .collect(Collectors.toSet());
             throw new CustomerCreateNotValidException(message, errorMessages);
         }
    }
}
