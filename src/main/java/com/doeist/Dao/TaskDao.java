package com.doeist.Dao;

import com.doeist.Database.Caches.CacheL1;
import com.doeist.Database.Caches.CacheL2;
import com.doeist.Database.FileHandler;
import com.doeist.Model.Task.Task;
import org.json.JSONObject;

import java.util.*;

public class TaskDao {

    private static CacheL1 cacheL1;

    private static Parser parser;
    private static FileHandler fileHandler;
    private static CacheL2 cacheL2;

    public TaskDao(){
        cacheL1=CacheL1.getInstance();
        fileHandler= FileHandler.getInstance();
        cacheL2=CacheL2.getInstence();
        parser = new Parser();

    }
    /** retrieve all recently accessed Tasks
     * that are stored in cache
     *
     * @Note MRA refers to most recently accessed tasks
     *
     * @return list of all Tasks in cache
     */
    public List<Task> getMRATasks(){
        Map<Integer, CacheL1.Node> entitiesMap=cacheL1.getEntitiesMap();
        List<Task> tasksList=new ArrayList<>();
        for(int i =0 ;i<entitiesMap.size();i++){
            Object node=entitiesMap.get(i).value;
            if (node.getClass().getName().equals("Task")){
                tasksList.add((Task) node);
            }
        }
        return tasksList;
    }

    /** retrieve all Tasks
     * that are stored in database
     *
     *
     *
     * @return list of all Tasks
     */
    public List<Task> getAllTask(){

        List<Task> allTasks=new ArrayList<>();
            Iterator<String> keys=fileHandler.getTasksJson().keys();
            while (keys.hasNext()){
                String key =keys.next();
                JSONObject task = (JSONObject) fileHandler.getTasksJson().get("" + key);
                Task task1 = parser.parseToTask(task);
                allTasks.add(task1);
            }

        return allTasks;

    }

    public Task getTaskById(int id){
       return (Task) cacheL1.getFromCache(id,"Task");
    }

    public void setTask(Task task){

        task.setDate(new Date());
        cacheL1.setIntoCache(task);
    }


    public void deleteTask(int id){
        cacheL2.deleteTask(id);
    }


    public void updateTask(int taskId, String description, boolean status, String note){
        cacheL2.UpdateCache(taskId,description,note,status);
    }




}
