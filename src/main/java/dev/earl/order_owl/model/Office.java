package dev.earl.order_owl.model;

import dev.earl.order_owl.model.embedded.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Office {

    @Id
    @GeneratedValue
    private Integer officeCode;
    private String phone;
    @Embedded
    private Address address;

    @OneToMany(mappedBy = "office")
    private List<Employee> employees;
}
