package com.HW._8.service;

import com.HW._8.domain.Employee;
import com.HW._8.exception.EmployeeAlreadyAddedException;
import com.HW._8.exception.EmployeeNotFoundException;
import com.HW._8.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employees;
    private final int MAX_EMPLOYEE_COUNT = 1000;

    public EmployeeService() {
        employees = new ArrayList<>();
    }

    public void addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEE_COUNT) {
            throw new EmployeeStorageIsFullException();
        }
        Employee target = new Employee(firstName, lastName);
        if (employees.contains(target)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(new Employee(firstName, lastName));
    }

    public void removeEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);
        if (!employees.contains(target)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(target);
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);
        if (!employees.contains(target)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(employees.indexOf(target));
    }

    public List<Employee> returnAllEmployees() {
        return employees;
    }
}
