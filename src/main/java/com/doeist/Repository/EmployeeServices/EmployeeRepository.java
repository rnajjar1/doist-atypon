package com.doeist.Repository.EmployeeServices;


import com.doeist.Model.Employee.Employee;

public interface EmployeeRepository {

    Employee getByEmail(String email);

    String getPasswordByEmail(String email);

    void addNewEmployee(Employee employee);

}
