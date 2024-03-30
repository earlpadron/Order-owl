package dev.earl.order_owl.service.validator;

import dev.earl.order_owl.exception.custom_exception.customer.CustomerCreateNotValidException;
import dev.earl.order_owl.model.dto.CustomerDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class EntityValidator<T> {

    /**
     * TODO
     *  - understand: what is the purpose of a custom validator?
     *  - is this the right way to creating validators?
     */
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = validatorFactory.getValidator();

    public Set<String> validate(T objectToValidate){

        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        Set<String> errorMessages = null;
        if(!violations.isEmpty()){
            return errorMessages =  violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
        }
        return errorMessages;
    }
}
