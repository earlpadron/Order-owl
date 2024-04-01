package dev.earl.order_owl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String title;
    @NotEmpty
    private String category;
    @NotEmpty
    @Column(columnDefinition = "Decimal(10,2) default '0.00'")
    private double price;
    @NotEmpty
    private String description;
    @NotEmpty
    private String image;
    @NotEmpty
    private int stock;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetailList;

}
