package dev.earl.order_owl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer productId;
    private String title;
    private String category;
    private double price;
    private String description;
    private String image;
    private int quantityInStock;


//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private ProductLine productLine;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
