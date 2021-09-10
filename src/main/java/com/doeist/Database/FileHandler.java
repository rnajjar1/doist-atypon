package com.doeist.Database;

import com.doeist.Model.Employee.Employee;
import com.doeist.Model.Task.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class FileHandler implements Operations {

    private static FileHandler fileHandler;
    private static JSONObject tasksJson;
    private static JSONObject employeeJson;
    private static final String TASKNAME ="com.doeist.Model.Task.Task";

    private FileHandler() {
    }

    public static FileHandler getInstance() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
            tasksJson=new JSONObject();
            employeeJson=new JSONObject();

        }
        return fileHandler;
    }

    public void UpdateFile(int id,Task task) {
        if(loadFromFile(id,"Task")!=null) {
            saveToFile(id,task);
        }
    }

    public void DeleteFromFile(int id) {
        if(loadFromFile(id,"Task")!=null) {
            tasksJson.remove("" + id);
            fileWriter(tasksJson,"Task");
        }
    }


    public void saveToFile(int key,Object obj) {
        if(obj.getClass().getName().equals(TASKNAME)){
            saveTask(key,(Task)obj);
        }else{
            System.out.println(obj.getClass().getName());
            saveEmployee(key,(Employee) obj);
        }


    }

    private void saveTask(int key,Task t ){
        JSONObject task1 = new JSONObject();
        task1.put("Employee",t.getEmployee());
        task1.put("taskDescription",t.getDescription());
        task1.put("Note",t.getNote());
        task1.put("Status",t.isStatus());
        task1.put("Priority",t.getPriority());
        task1.put("id",key);
        task1.put("Date",t.getDate());
        tasksJson.put(""+key,task1);
        fileWriter(tasksJson,"Task");

    }

    private void saveEmployee(int key,Employee e){
        JSONObject employee = new JSONObject();
        employee.put("Name",e.getName());
        employee.put("Email",e.getEmail());
        employee.put("Password",e.getPassword());
        employeeJson.put(""+key,employee);
        fileWriter(employeeJson,"Employee");


    }
    public void fileWriter(JSONObject o,String table){
        try {
            synchronized (this) {
                Path p = Paths.get(table+".json");
                Files.write(p, Collections.singleton(o.toString()));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public Object loadFromFile(int id,String table) {
        if (table.equals("Task")){
            return loadTask(id);
        }else
            return loadEmployee( id);
    }
    private Task  loadTask(int id){
        JSONObject task=(JSONObject) tasksJson.get(""+id);
        Gson gson = new GsonBuilder()
                .setDateFormat("E MMM dd HH:mm:ss Z yyyy")
                .create();
        String json_string = gson.toJson(task);
        return gson.fromJson(json_string, Task.class);

    }

    private Employee loadEmployee(int id){
        JSONObject employee=(JSONObject) employeeJson.get(""+id);
        Gson gson = new GsonBuilder().create();
        String json_string = gson.toJson(employee);
        return gson.fromJson(json_string, Employee.class);

    }

    public  JSONObject getTasksJson() {
        return tasksJson;
    }

    public  JSONObject getEmployeeJson() {
        return employeeJson;
    }
}
