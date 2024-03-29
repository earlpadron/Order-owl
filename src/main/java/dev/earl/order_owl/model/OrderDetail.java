package dev.earl.order_owl.model;

import dev.earl.order_owl.model.embedded.OrderDetailId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail{

    @EmbeddedId
    private OrderDetailId orderDetailId;
    private int quantityOrdered;
    private float priceEach;
    private int orderLineNumber;

    @ManyToOne
    @MapsId(value = "orderNumber")
    @JoinColumn(name = "order_id")
    private Order order;
}
