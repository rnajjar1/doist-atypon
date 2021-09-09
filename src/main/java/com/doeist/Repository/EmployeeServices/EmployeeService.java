package com.doeist.Repository.EmployeeServices;

import com.doeist.Dao.EmployeeDao;
import com.doeist.Model.Employee.Employee;

import java.util.List;

public class EmployeeService implements EmployeeRepository {

    private EmployeeDao employeeDao;

    public EmployeeService(){
        employeeDao=new EmployeeDao();
    }

    @Override
    public Employee getByEmail(String email) {

        System.out.println("emaillll" +email);
        List<Employee> employeesList= employeeDao.getAllEmployee();
        System.out.println(" employee list size "+employeesList.size());
        for (int i =0;i<employeesList.size();i++){
            System.out.println(employeesList.get(i).getEmail());
            System.out.println(email.equals(employeesList.get(i).getEmail()));
            if (email.equals(employeesList.get(i).getEmail())){
                System.out.println("we should be here");
                return employeesList.get(i);
            }
        }
        System.out.println("we should not be here");
//        for (Employee employee : employeesList) {
//            if (email.equals(employee.getEmail())){
//                return employee;
//            }
//        }
        return null;
    }

    @Override
    public String getPasswordByEmail(String email) {
        List<Employee> employeesList= employeeDao.getAllEmployee();
        for (Employee employee : employeesList) {
            if (email.equals(employee.getEmail())){
                return employee.getPassword();
            }
        }
        return null;
    }

    // TODO: ٠١/٠٩/٢٠٢١ you need to implement either an admin or update and delete
    @Override
    public void addNewEmployee(Employee employee) {
        employeeDao.setNewEmployee(employee);
    }
}
