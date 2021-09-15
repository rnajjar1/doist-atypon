package com.doeist.Dao;

import com.doeist.Model.Employee.Employee;
import com.doeist.Model.Employee.EmployeeBuilder;
import com.doeist.Model.Task.Task;
import com.doeist.Model.Task.TaskBuilder;
import org.json.JSONObject;

import java.util.Date;

public class Parser {



    public Employee parseToEmployee(JSONObject jsonObject){
        String name=jsonObject.getString("Name");
        String email=jsonObject.getString("Email");
        String password=jsonObject.getString("Password");
        return new EmployeeBuilder().setEmail(email)
                .setPassword(password).setName(name).getEmployee();
    }

    public Task parseToTask(JSONObject jsonObject){
        String priority=jsonObject.getString("Priority");
        String taskDescription =jsonObject.getString("taskDescription");
        String note=jsonObject.getString("Note");
        boolean status=jsonObject.getBoolean("Status");
        Date date=(Date) jsonObject.get("Date");
        int id=jsonObject.getInt("id");

        Employee employee=(Employee) jsonObject.get("Employee");

        return new TaskBuilder().setDescription(taskDescription).setNote(note)
                .setStatus(status).setPriority(priority).setDate(date)
                .setEmployee(employee).setId(id).getTask();

    }





}
