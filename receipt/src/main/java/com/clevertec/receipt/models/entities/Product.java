package com.clevertec.receipt.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product {

    @Id
    private Long id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "has_discount")
    private boolean hasDiscount;

}
