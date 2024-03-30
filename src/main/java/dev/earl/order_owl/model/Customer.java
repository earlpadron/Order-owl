package dev.earl.order_owl.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.earl.order_owl.model.embedded.Address;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer customerId;

    @NotEmpty(message = "{customer.name.not.empty}")
    private String name;

    @NotEmpty(message = "{customer.email.not.empty}")
    @Email
    private String email;

    private String phone;
    @Min(value = 0)
    private double creditLimit;

    @Embedded
    @Valid
    private Address address;

//
//    @ManyToOne
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
//
//    @OneToMany(mappedBy = "customer")
//    @JsonManagedReference
//    private List<Payment> payment; //attempting bi-directional relationship with Payment but is causing : LazyInitializationException
}
