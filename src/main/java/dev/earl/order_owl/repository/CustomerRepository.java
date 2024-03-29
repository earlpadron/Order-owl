package dev.earl.order_owl.repository;

import dev.earl.order_owl.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByCustomerNameIgnoreCase(String name);
}
