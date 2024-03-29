package dev.earl.order_owl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer productCode;
    private String productName;

    private String productVendor;
    private String productDescription;
    private int quantityInStock;
    private float buyPrice;
    private float msrp;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductLine productLine;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
