package dev.earl.order_owl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;

    @ManyToOne //bi-directional relationship with office
    @JoinColumn(name = "office_id")
    private Office office;

    @OneToMany(mappedBy = "reportsTo") //notice that this is a self-relation
    @JsonManagedReference
    private Set<Employee> inChargeOf;

    private String jobTitle;

    @ManyToOne
    @JsonBackReference
    private Employee reportsTo; //self relation



}
