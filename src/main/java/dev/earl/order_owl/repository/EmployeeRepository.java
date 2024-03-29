package dev.earl.order_owl.repository;

import dev.earl.order_owl.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
