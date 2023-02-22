package com.clevertec.receipt.models.entities;

public class EmployeeCard {

    private long id;
    private String number;
    private Responsibility responsibility;

    static class Responsibility {
        private char level;
        private boolean insurance;

    }
}
