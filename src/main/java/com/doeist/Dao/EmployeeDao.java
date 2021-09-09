package com.doeist.Dao;

import com.doeist.Database.Caches.CacheL1;
import com.doeist.Database.FileHandler;
import com.doeist.Model.Employee.Employee;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class EmployeeDao {

    private Parser parser;
    private static CacheL1 cacheL1;

    private static FileHandler fileHandler;

    public EmployeeDao() {
        cacheL1 = CacheL1.getInstance();
        fileHandler = FileHandler.getInstance();
        parser = new Parser();
    }


    /**
     * retrieve all recently accessed Employees
     * that are stored in cache
     *
     * @return list of all employees in cache
     * @Note MRA refers to most recently accessed employee
     */

    public List<Employee> getMRAemployees() {
        Map<Integer, CacheL1.Node> entitiesMap = cacheL1.getEntitiesMap();
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < entitiesMap.size(); i++) {
            Object node = entitiesMap.get(i).value;
            if (node.getClass().getName().equals("Employee")) {
                employeeList.add((Employee) node);
            }
        }
        return employeeList;
    }

    /**
     * retrieve all Employee
     * that are stored in database
     *
     * @return list of all Employee
     */

    public List<Employee> getAllEmployee() {
        List<Employee> allEmployee = new ArrayList<>();


        Iterator<String> keys=fileHandler.getEmployeeJson().keys();
        System.out.println("number of keys " +keys.hasNext());
        while (keys.hasNext()){
            String key =keys.next();
            JSONObject employee = (JSONObject) fileHandler.getEmployeeJson().get("" + key);
            Employee employee1 = parser.parseToEmployee(employee);
            allEmployee.add(employee1);
        }

        return allEmployee;

    }


    public void setNewEmployee(Employee employee) {
        cacheL1.setIntoCache(employee);
    }


}
