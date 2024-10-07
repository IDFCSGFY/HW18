package com.HW._8.service;

import com.HW._8.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findHighestSalary(int department);

    Employee findLowestSalary(int department);

    List<Employee> getWholeDepartment(int department);

    Map<Integer, ArrayList<Employee>> getAllConsideringDepartments();
}
