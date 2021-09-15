package com.doeist.Repository.TaskServices;

import com.doeist.Dao.TaskDao;
import com.doeist.Model.Task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskService implements TaskRepository,IRead,IWrite {

    private TaskDao taskDao;

    public TaskService() {
        taskDao = new TaskDao();
    }

    @Override
    public List<Task> getAllMyTasks(String email) {
        List<Task> taskList = taskDao.getAllTask();
        System.out.println("size of  task list" + taskList.size());
        List<Task> myTasksList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getEmployee().getEmail().equals(email)) {
                myTasksList.add(task);
            }
        }
        System.out.println("myTasksList"+taskList.size());
        return myTasksList;
    }

    @Override
    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);

    }

    @Override
    public void createTask(Task task) {
        taskDao.setTask(task);

    }

    @Override
    public void deleteTask(int id) {
        taskDao.deleteTask(id);
    }

    @Override
    public List<Task> getTasksSortedByDate() {
        List<Task> allTask = taskDao.getAllTask();
        Collections.sort(allTask);
        return allTask;
    }

    @Override
    public List<Task> getTaskSortedByPriority() {
            List<Task> allTask = taskDao.getAllTask();
            Collections.sort(allTask, new Task());
            return allTask;
    }

    @Override
    public void updateTask(int id, String description, boolean status, String note) {
        taskDao.updateTask(id, description, status, note);

    }

    @Override
    public List<Task> getAllTasks() {
      return taskDao.getAllTask();
    }
}
