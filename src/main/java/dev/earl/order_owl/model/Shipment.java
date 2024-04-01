package dev.earl.order_owl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.earl.order_owl.model.converter.Status;
import dev.earl.order_owl.model.embedded.Address;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Shipment {

    @Id
    @GeneratedValue
    private Integer shipmentId;

    @FutureOrPresent(message = "{shipment.date.not.valid}")
    private LocalDate shipmentDate;
    private Status status; //AttributeConverter automatically converts the type from : Entity <-> DB column representation

    @Embedded
    @Valid
    private Address address;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
}
