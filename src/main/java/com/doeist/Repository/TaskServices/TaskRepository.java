package com.doeist.Repository.TaskServices;

import com.doeist.Model.Task.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> getAllMyTasks(String email);

    void updateTask(int id ,String Description,boolean status,String note);

    void deleteTask(int id);

    List<Task> getTasksSortedByDate();

    List<Task> getTaskSortedByPriority();


}
