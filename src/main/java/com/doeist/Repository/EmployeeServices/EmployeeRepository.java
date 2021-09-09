package com.doeist.Repository.EmployeeServices;


import com.doeist.Model.Employee.Employee;

// TODO: ٠١/٠٩/٢٠٢١ create an employee builder and task builder
public interface EmployeeRepository {

    Employee getByEmail(String email);

    String getPasswordByEmail(String email);

    void addNewEmployee(Employee employee);

}
