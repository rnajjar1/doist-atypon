package com.doeist.Repository.EmployeeServices;

import com.doeist.Dao.EmployeeDao;
import com.doeist.Model.Employee.Employee;

import java.util.List;

public class EmployeeService implements EmployeeRepository {

    private EmployeeDao employeeDao;

    public EmployeeService() {
        employeeDao = new EmployeeDao();
    }
    @Override
    public Employee getByEmail(String email) {
        List<Employee> employeesList = employeeDao.getAllEmployee();
        for (int i = 0; i < employeesList.size(); i++) {
            if (email.equals(employeesList.get(i).getEmail())) {
                return employeesList.get(i);
            }
        }
        return null;
    }

    @Override
    public String getPasswordByEmail(String email) {
        List<Employee> employeesList = employeeDao.getAllEmployee();
        for (Employee employee : employeesList) {
            if (email.equals(employee.getEmail())) {
                return employee.getPassword();
            }
        }
        return null;
    }

    // TODO: ٠١/٠٩/٢٠٢١ you need to implement an admin to add employee
    @Override
    public void addNewEmployee(Employee employee) {
        employeeDao.setNewEmployee(employee);
    }
}
