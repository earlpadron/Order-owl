package dev.earl.order_owl.repository;

import dev.earl.order_owl.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
