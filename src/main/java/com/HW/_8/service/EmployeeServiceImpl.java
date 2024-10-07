package com.HW._8.service;

import com.HW._8.model.Employee;
import com.HW._8.exception.BadParamException;
import com.HW._8.exception.EmployeeAlreadyAddedException;
import com.HW._8.exception.EmployeeNotFoundException;
import com.HW._8.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;
    private final int MAX_EMPLOYEE_COUNT = 1000;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEE_COUNT) {
            throw new EmployeeStorageIsFullException();
        }
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new BadParamException();
        }
        Employee target = new Employee(firstName, lastName);
        String tempKey = String.join("_", firstName, lastName);
        if (employees.containsKey(tempKey)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(tempKey, target);
        return target;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);
        String tempKey = String.join("_", firstName, lastName);
        if (!employees.containsKey(tempKey)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(tempKey);
        return target;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);
        String tempKey = String.join("_", firstName, lastName);
        if (!employees.containsKey(tempKey)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(tempKey);
    }

    public Map<String, Employee> returnAllEmployees() {
        return Map.copyOf(employees);
    }
}
