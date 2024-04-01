package dev.earl.order_owl.model;

import dev.earl.order_owl.model.converter.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue
    private Integer orderId;

    @NotNull(message = "{order.date.not.null}")
    @FutureOrPresent(message = "{order.date.not.valid}")
    private LocalDate orderDate;

    @NotNull(message = "{order.text.not.null}")
    private String text;

    @NotNull(message = "{order.customer.not.null}")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
