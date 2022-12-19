package com.clevertec.receipt.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "receipts")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Data
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receipts_generator")
    @SequenceGenerator(name = "receipts_generator", sequenceName = "receipts_seq", allocationSize = 1)
    private Long id;

    @Column(name = "json")
    private String json;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;
}
