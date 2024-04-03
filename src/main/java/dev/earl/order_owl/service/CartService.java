package dev.earl.order_owl.service;

import dev.earl.order_owl.exception.custom_exception.cart.CartAlreadyExistsException;
import dev.earl.order_owl.exception.custom_exception.cart.CartConstraintViolationException;
import dev.earl.order_owl.exception.custom_exception.cart.CartInvalidQuantityException;
import dev.earl.order_owl.exception.custom_exception.cart.CartNotFoundException;
import dev.earl.order_owl.model.Cart;
import dev.earl.order_owl.model.Product;
import dev.earl.order_owl.repository.CartRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@PropertySource(value = "classpath:exceptionMessages.properties")
public class CartService {

    private final CartRepository cartRepository;
    private final Validator validator;
    private final Environment environment;

    public CartService(CartRepository cartRepository, Validator validator, Environment environment) {
        this.cartRepository = cartRepository;
        this.validator = validator;
        this.environment = environment;
    }

    public Cart getCart(Integer id) throws CartNotFoundException {
        return cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(environment.getProperty("service.cart.not.found")));
    }

    public Cart createCart(Cart newCart) throws CartAlreadyExistsException, CartConstraintViolationException {
        //validate the new cart
        Set<ConstraintViolation<Cart>> violations = validator.validate(newCart);
        if(!violations.isEmpty()){
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new CartConstraintViolationException(environment.getProperty("service.cart.invalid.create"), errorMessages);
        }
        //check if it exists
        if(cartRepository.existsById(newCart.getCartId())){
            throw new CartAlreadyExistsException((environment.getProperty("service.cart.already.exists")));
        }

        return cartRepository.save(newCart);
    }

    public Cart updateFullCart(Integer id, Cart updatedCart) throws CartNotFoundException, CartConstraintViolationException{
        Set<ConstraintViolation<Cart>> violations = validator.validate(updatedCart);
        if(!violations.isEmpty()){
            Set<String> errorMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new CartConstraintViolationException(environment.getProperty("service.cart.invalid.update"), errorMessages);
        }

        Cart cartToUpdate = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(environment.getProperty("service.cart.not.found")));

        cartToUpdate.setCustomer(updatedCart.getCustomer());
        cartToUpdate.setProductToQuantity(updatedCart.getProductToQuantity());
        cartToUpdate.setSubtotal(updatedCart.getSubtotal());
        cartToUpdate.setNumberOfItems(updatedCart.getNumberOfItems());

        return cartRepository.save(cartToUpdate);
    }
    // TODO - should only send a Map<Product,Integer> instead of updating the full cart, create a separate method for updating the full cart
    public Cart updateProductsInCart(Integer id, Map<Product, Integer> productToQuantity)
            throws CartNotFoundException, CartConstraintViolationException, CartInvalidQuantityException {


        //search for cart if it exists
        Cart cartToUpdate = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(environment.getProperty("service.cart.not.found")));

        //update the product and quantity map
        int numberOfItems = productToQuantity.size();
        double subTotal = 0;
        for(Map.Entry<Product, Integer> entry : productToQuantity.entrySet()){
            Product product = entry.getKey();
            //validate the product to be added
            Set<ConstraintViolation<Product>> violations = validator.validate(product);
            if(!violations.isEmpty()){
                Set<String> errorMessages = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toSet());
                throw new CartConstraintViolationException(environment.getProperty("service.cart.invalid.product"), errorMessages);
            }
            double productPrice = product.getPrice();
            int quantity = entry.getValue();
            //validate the quantity
            if(quantity < 0){
                throw new CartInvalidQuantityException(environment.getProperty("service.cart.invalid.quantity"));
            };

            subTotal += productPrice * quantity;
        }

        //update the cart
        cartToUpdate.setSubtotal(subTotal);
        cartToUpdate.setNumberOfItems(numberOfItems);
        cartToUpdate.setProductToQuantity(productToQuantity);

        return cartRepository.save(cartToUpdate);
    }

    public void deleteCart(Integer id) throws CartNotFoundException{
        //check if it exists
        if(!cartRepository.existsById(id)){
            throw new CartNotFoundException(environment.getProperty("service.cart.not.found"));
        }
        cartRepository.deleteById(id);

    }
}
