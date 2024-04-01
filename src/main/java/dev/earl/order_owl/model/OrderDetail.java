package dev.earl.order_owl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail{

    @Id
    @GeneratedValue
    private Integer orderDetailId;
    @Min(0)
    private int quantity;
    private float priceEach;

    @ManyToOne
    //@MapsId(value = "orderId") this lets spring know the embedded id is the foreign key column
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
