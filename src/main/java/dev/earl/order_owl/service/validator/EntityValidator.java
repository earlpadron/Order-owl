package dev.earl.order_owl.service.validator;

import dev.earl.order_owl.exception.custom_exception.customer.CustomerCreateNotValidException;
import dev.earl.order_owl.model.dto.CustomerDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.validation.ValidationUtils;

import java.util.Set;
import java.util.stream.Collectors;

public class EntityValidator<T> {

    /**
     * TODO
     *  - understand: what is the purpose of a custom validator?
     *  - is this the right way to creating validators?
     *  - https://howtodoinjava.com/spring/spring-programmatic-validator/ follow this link for more info on programmatic validation
     *  - more info on valiation https://reflectoring.io/bean-validation-with-spring-boot/#returning-structured-error-responses
     */
    private final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory(); //this way of doing it does not have Spring support
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
