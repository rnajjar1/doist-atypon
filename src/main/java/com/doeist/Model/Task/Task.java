package com.doeist.Model.Task;

import com.doeist.Model.Employee.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Task implements Comparable<Task>, Comparator<Task> {

private Employee employee;
private String note;
private String description;
private boolean status;
private Date Date;
private int id;
private String priority;

private List<String> definedOrder = // define my custom order
            Arrays.asList("High", "Medium", "Low");

public Task(){


}
    public Task(Employee employee, String note, String description,
                boolean status, java.util.Date date, int id, String priority) {
        this.employee = employee;
        this.note = note;
        this.description = description;
        this.status = status;
        Date = date;
        this.id = id;
        this.priority = priority;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Task o) {
        return getDate().compareTo(o.getDate());
    }

    @Override
    public int compare(Task o1, Task o2) {

        return Integer.compare(definedOrder.indexOf(o1.getPriority()), definedOrder.indexOf(o2.getPriority()));

    }

    @Override
    public String toString() {
        return "Task{" +
                "employee=" + employee +
                ", note='" + note + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", Date=" + Date +
                ", id=" + id +
                ", priority='" + priority + '\'' +
                ", definedOrder=" + definedOrder +
                '}';
    }
}
