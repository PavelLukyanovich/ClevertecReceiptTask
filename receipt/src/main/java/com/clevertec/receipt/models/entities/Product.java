package com.clevertec.receipt.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    private Long id;
    @Column(name = "title",length = 10)
    private String title;
    @Column(name = "price")
    private Double price;
    @Column(name = "has_discount")
    private boolean hasDiscount;

}
