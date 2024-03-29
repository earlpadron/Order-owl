package dev.earl.order_owl.repository;

import dev.earl.order_owl.model.Payment;
import dev.earl.order_owl.model.embedded.PaymentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, PaymentId> {
}
