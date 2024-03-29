package dev.earl.order_owl.model;

import dev.earl.order_owl.model.converter.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue
    private Integer orderNumber;
    @NotNull
    @FutureOrPresent
    private LocalDate orderDate;
    @NotNull
    @FutureOrPresent
    private LocalDate shippedDate;

    @NotNull
    private String text;
    private Status status; //AttributeConverter automatically converts the type from : Entity <-> DB column representation

    @OneToMany(mappedBy = "order")
    private List<Product> productList;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_number")
    private Customer customer;
}
