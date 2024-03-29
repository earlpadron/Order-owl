package dev.earl.order_owl.repository;

import dev.earl.order_owl.model.OrderDetail;
import dev.earl.order_owl.model.embedded.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
