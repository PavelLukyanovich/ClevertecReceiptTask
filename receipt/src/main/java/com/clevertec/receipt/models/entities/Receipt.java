package com.clevertec.receipt.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Date;
@Data
@Entity
@Table(name = "receipt")
@NoArgsConstructor
public class Receipt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @GeneratedValue()
    @Column(name = "cashier_id", length = 3)
    private String cashierId;
    @Column(name = "receipt_title")
    private String title;
    @Column(name = "amount_of_product", length = 5)
    private Integer productAmount;
    @Column(name = "price_of_product", length = 10)
    private Integer productPrice;
    @Column(name = "total_price_of_product", length = 10)
    private Integer totalProductPrice;
    @Column(name = "total_price_of_receipt", length = 10)
    private Integer totalReceiptPrice;
    @Column(name = "discount", length = 2)
    private Integer discount;
    @Column(name = "date")
    private Date date;
    @Column(name = "time")
    private Timestamp timestamp;
}
