package com.HW._8.domain;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;

    public Employee (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return this.firstName.equals(employee.firstName)&&this.lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lastName);
    }

    @Override
    public String toString() {
        return String.join(" ", lastName, firstName);
    }
}
