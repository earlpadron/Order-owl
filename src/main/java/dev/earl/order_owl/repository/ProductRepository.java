package dev.earl.order_owl.repository;

import dev.earl.order_owl.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
