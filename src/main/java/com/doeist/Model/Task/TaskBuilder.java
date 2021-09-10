package com.doeist.Model.Task;

import com.doeist.Model.Employee.Employee;

import java.util.Date;

public class TaskBuilder {

    private Employee employee;
    private String note;
    private String description;
    private boolean status;
    private Date date;
    private int id;
    private String priority;

    public Task getTask() {
        return new Task(employee, note, description,
                status, date, id, priority);
    }

    public TaskBuilder setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public TaskBuilder setNote(String note) {
        this.note = note;
        return this;
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public TaskBuilder setDate(Date date) {
        this.date = date;
        return this;
    }

    public TaskBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public TaskBuilder setPriority(String priority) {
        this.priority = priority;
        return this;
    }


}
