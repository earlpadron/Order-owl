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
    @Min(value = 0, message = "{orderDetail.quantity.invalid}")
    private int quantity;
    @Min(value = 0, message = "{orderDetail.priceEach.invalid}")
    private float priceEach;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
