package dev.earl.order_owl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.earl.order_owl.model.embedded.PaymentId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Payment{

    @EmbeddedId
    private PaymentId paymentId;
    @PastOrPresent(message = "{payment.date.invalid}")
    private LocalDate paymentDate;
    @Min(value = 0, message = "{payment.amount.invalid}")
    private double amount;

    @ManyToOne
    @MapsId("customerId") //this specifies the attribute in the composite primary key to which the relationship attribute corresponds to
    @JoinColumn(name = "customer_id") //Unidirectional relationship with Customer(Actually don't need @JoinColumn but can specify a name)
    //@JsonBackReference
    private Customer customer;

}
