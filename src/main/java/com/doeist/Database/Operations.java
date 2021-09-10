package com.doeist.Database;

import com.doeist.Model.Task.Task;

public interface Operations {

    void DeleteFromFile(int id);

    void saveToFile(int key, Object obj);

    Object loadFromFile(int id, String table);

    void UpdateFile(int id, Task task);
}
