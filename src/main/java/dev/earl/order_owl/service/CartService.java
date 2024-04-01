package dev.earl.order_owl.service;

import dev.earl.order_owl.model.Cart;
import dev.earl.order_owl.model.Product;
import dev.earl.order_owl.repository.CartRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final Validator validator;
    private final Environment environment;

    public CartService(CartRepository cartRepository, Validator validator, Environment environment) {
        this.cartRepository = cartRepository;
        this.validator = validator;
        this.environment = environment;
    }

    public Cart getCart(Integer id){
        return cartRepository.findById(id)
                .orElseThrow();
    }

    public Cart createCart(Cart newCart){
        Set<ConstraintViolation<Cart>> violations = validator.validate(newCart);
        //check if it exists
        return cartRepository.save(newCart);
    }

    public Cart updateFullCart(Integer id, Cart updatedCart){
        Set<ConstraintViolation<Cart>> violations = validator.validate(updatedCart);
        if(!violations.isEmpty()){
            //throw exception here
        }
        Cart cartToUpdate = cartRepository.findById(id)
                .orElseThrow();

        cartToUpdate.setCustomer(updatedCart.getCustomer());
        cartToUpdate.setProductToQuantity(updatedCart.getProductToQuantity());
        cartToUpdate.setSubtotal(updatedCart.getSubtotal());
        cartToUpdate.setNumberOfItems(updatedCart.getNumberOfItems());

        return cartRepository.save(cartToUpdate);
    }
    // TODO - should only send a Map<Product,Integer> instead of updating the full cart, create a separate method for updating the full cart
    public Cart updateProductsInCart(Integer id, Map<Product, Integer> productToQuantity){

        Cart cartToUpdate = cartRepository.findById(id)
                .orElseThrow();

        int numberOfItems = productToQuantity.size();
        double subTotal = 0;
        for(Map.Entry<Product, Integer> entry : productToQuantity.entrySet()){
            Product product = entry.getKey();
            Set<ConstraintViolation<Product>> violations = validator.validate(product);
            if(!violations.isEmpty()){
                //throw invalid product exception here
            }
            double productPrice = product.getPrice();
            int quantity = entry.getValue();
            if(quantity < 0) System.out.println("invalid quanitity, throw an exception here");

            subTotal += productPrice * quantity;
        }

        cartToUpdate.setSubtotal(subTotal);
        cartToUpdate.setNumberOfItems(numberOfItems);
        cartToUpdate.setProductToQuantity(productToQuantity);

        return cartRepository.save(cartToUpdate);
    }

    public void deleteCart(Integer id){
        //check if it exists
        cartRepository.deleteById(id);

    }
}
