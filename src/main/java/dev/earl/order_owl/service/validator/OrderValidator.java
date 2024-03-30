package dev.earl.order_owl.service.validator;

import dev.earl.order_owl.exception.custom_exception.customer.CustomerCreateNotValidException;
import dev.earl.order_owl.model.dto.CustomerDTO;
import dev.earl.order_owl.model.dto.OrderDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderValidator extends EntityValidator<OrderDTO>{
    public void validate(OrderDTO orderDto, String message){
        Set<String> errorMessages = super.validate(orderDto);
        if(errorMessages != null){
            throw new CustomerCreateNotValidException(message, errorMessages);
        }
    }
}
