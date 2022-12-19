package com.clevertec.receipt.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "card_number", unique = true, length = 5)
    private String cardNumber;

    @Column(name = "discount")
    private Integer discount;
}



