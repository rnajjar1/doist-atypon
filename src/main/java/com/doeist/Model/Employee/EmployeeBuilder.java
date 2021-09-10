package com.doeist.Model.Employee;

import org.springframework.context.annotation.Bean;

public class EmployeeBuilder {

    private String name;
    private String email;
    private String password;
    private String matchingPassword;
    private int id;

    @Bean
    public Employee getEmployee() {
        return new Employee(name, email, password, matchingPassword);
    }

    public EmployeeBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public EmployeeBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
        return this;
    }
}
