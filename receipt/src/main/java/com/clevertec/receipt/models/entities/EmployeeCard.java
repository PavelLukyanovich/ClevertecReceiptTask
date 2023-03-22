package com.clevertec.receipt.models.entities;

import lombok.Data;

import java.io.Serializable;
@Data
public class EmployeeCard implements Serializable {
    private long id;

    private String number;

    public EmployeeCard(long id, String number) {
        this.id = id;
        this.number = number;
    }
}
