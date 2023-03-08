package com.clevertec.receipt.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@Data
public class Employee implements Serializable {

    private String firstName;
    private String lastName;
    private int age;
    private boolean haveEmployeeCard;
    private EmployeeCard employeeCard;
    private char skillLevel;
    private Enum position;

    public Employee(String firstName, String lastName, int age, boolean haveEmployeeCard, EmployeeCard employeeCard, char skillLevel,  Enum position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.haveEmployeeCard = haveEmployeeCard;
        this.employeeCard = employeeCard;
        this.skillLevel = skillLevel;
        this.position = position;
    }
}
