package com.HW._8.service;

import com.HW._8.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findHighestSalary(int department) {
        return employeeService.getAllEmployees().values().stream()
                .filter(e->e.getDepartmentID()==department)
                .max(Comparator.comparing(Employee::getWage))
                .get();
    }

    @Override
    public Employee findLowestSalary(int department) {
        return employeeService.getAllEmployees().values().stream()
                .filter(e->e.getDepartmentID()==department)
                .min(Comparator.comparing(Employee::getWage))
                .get();
    }

    @Override
    public List<Employee> getWholeDepartment(int department) {
        return employeeService.getAllEmployees().values().stream()
                .filter(e->e.getDepartmentID()==department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, ArrayList<Employee>> getAllConsideringDepartments() {
        return employeeService.getAllEmployees().values().stream()
                .collect(Collectors.toMap(
                        Employee::getDepartmentID,
                        e->new ArrayList<>(List.of(e)),
                        (x, y) -> new ArrayList<>(Stream.concat(x.stream(), y.stream()).collect(Collectors.toList()))
                ));
    }


    // localhost:8080/employee/addfull?firstName=Aaa&lastName=Aaa&department=1&wage=10000
    // localhost:8080/employee/addfull?firstName=Bbb&lastName=Bbb&department=1&wage=20000
    // localhost:8080/employee/addfull?firstName=Ccc&lastName=Ccc&department=1&wage=30000
    // localhost:8080/employee/addfull?firstName=Ddd&lastName=Ddd&department=2&wage=20000
}
