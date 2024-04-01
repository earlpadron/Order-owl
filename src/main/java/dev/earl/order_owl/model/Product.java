package dev.earl.order_owl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    private int stock;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetailList;

}
