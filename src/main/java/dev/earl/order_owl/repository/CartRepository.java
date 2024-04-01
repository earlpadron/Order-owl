package dev.earl.order_owl.repository;

import dev.earl.order_owl.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
