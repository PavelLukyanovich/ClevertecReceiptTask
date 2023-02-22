package com.clevertec.receipt.parser;

public class EmployeeCard {

    private long id;
    private String number;
    private Responsibility responsibility;

    class Responsibility {
        private char level;
        private boolean insurance;
    }
}
