package com.clevertec.receipt.models.entities;

import java.io.Serializable;

public class EmployeeCard implements Serializable {
    private long id;

    private String number;

    public EmployeeCard(long id, String number) {
        this.id = id;
        this.number = number;
    }
}
