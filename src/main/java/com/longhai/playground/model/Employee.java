package com.longhai.playground.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(EmployeeView.Summary.class)
    private Long id;
    @JsonView(EmployeeView.Summary.class)
    private String name;
    private int salary;
    @JsonView(EmployeeView.Summary.class)
    private Long managerId;

    public Employee() {
    }

    public Employee(Long id, String name, int salary, Long managerId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}


