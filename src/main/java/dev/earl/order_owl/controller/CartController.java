package dev.earl.order_owl.controller;

import dev.earl.order_owl.exception.custom_exception.cart.CartConstraintViolationException;
import dev.earl.order_owl.exception.custom_exception.cart.CartInvalidQuantityException;
import dev.earl.order_owl.exception.custom_exception.cart.CartNotFoundException;
import dev.earl.order_owl.model.Cart;
import dev.earl.order_owl.model.Product;
import dev.earl.order_owl.service.CartService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
@RequestMapping("order_owl/v1/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/{cart_id}")
    public ResponseEntity<Cart> getCart(@PathVariable(value = "cart_id") @Min(1) Integer id) throws CartNotFoundException {
        Cart cart = cartService.getCart(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping(value = "/{cart_id}")
    public ResponseEntity<Cart> updateCart(@PathVariable(value = "cart_id") @Min(1) Integer id, @RequestBody Map<Product, Integer> productToQuantity)
    throws CartNotFoundException, CartConstraintViolationException, CartInvalidQuantityException {
        Cart updatedCart = cartService.updateProductsInCart(id, productToQuantity);
        return new ResponseEntity<>(updatedCart, HttpStatus.ACCEPTED);
    }
}
