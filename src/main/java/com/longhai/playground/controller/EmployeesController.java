package com.longhai.playground.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.longhai.playground.model.Employee;
import com.longhai.playground.model.EmployeeRepository;
import com.longhai.playground.model.EmployeeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmployeesController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("admin/employees")
    public Iterable<Employee> adminGetEmployees() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @JsonView(EmployeeView.Summary.class)
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        Iterable<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @GetMapping("/me")
    public Employee getMe(@AuthenticationPrincipal Employee employee) {
        return employee;
    }

}