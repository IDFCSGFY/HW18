package com.HW._8.service;

import com.HW._8.model.Employee;
import com.HW._8.exception.BadParamException;
import com.HW._8.exception.EmployeeAlreadyAddedException;
import com.HW._8.exception.EmployeeNotFoundException;
import com.HW._8.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees;
    private final int MAX_EMPLOYEE_COUNT = 1000;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEE_COUNT) {
            throw new EmployeeStorageIsFullException();
        }
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new BadParamException();
        }
        Employee target = new Employee(firstName, lastName);
        if (employees.contains(target)) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(new Employee(firstName, lastName));
        return target;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);
        if (!employees.contains(target)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(target);
        return target;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee target = new Employee(firstName, lastName);
        if (!employees.contains(target)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(employees.indexOf(target));
    }

    public List<Employee> returnAllEmployees() {
        return Collections.unmodifiableList(employees);
    }
}
