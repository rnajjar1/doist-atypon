package com.doeist.Database.Caches;

import com.doeist.Database.FileHandler;
import com.doeist.Database.IdGenerator;
import com.doeist.Model.Task.Task;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheL2 {

    private static CacheL1 cacheL1;
    private static FileHandler fileHandler;
    private static ReentrantReadWriteLock lock;
    private static ConcurrentHashMap<Integer, CacheL1.Node> entityMap;
    private static int key;
    private static IdGenerator idGenerator;
    private static CacheL2 cacheL2;

    private CacheL2() {

    }

    public static CacheL2 getInstance() {
        if (cacheL2 == null) {
            cacheL2 = new CacheL2();
            cacheL1 = CacheL1.getInstance();
            fileHandler = FileHandler.getInstance();
            lock = new ReentrantReadWriteLock();
            idGenerator=IdGenerator.getIdInstance();
            entityMap = CacheL1.getEntitiesMap();
        }
        return cacheL2;
    }

    /**
     * Delete the task either from CacheL1 and File
     *
     * @param taskId represents ID of a task
     */

    public void deleteTask(int taskId) {
        if (idGenerator.isReserved(taskId)) {
            lock.writeLock().lock();
            if (entityMap.get(taskId) != null) {
                cacheL1.deleteNode(entityMap.get(taskId));
                entityMap.remove(taskId);
            }
            idGenerator.deleteKey(taskId);
            fileHandler.DeleteFromFile(taskId);
            lock.writeLock().unlock();
        }

    }


    /**
     * Update the task either from CacheL1 and File
     *
     * @param taskId represents ID of a task
     */
    public void UpdateCache(int taskId, String description, String note, boolean status) {

        if (idGenerator.isReserved(taskId)) {
            Task task = (Task) entityMap.get(taskId).value;
            task.setDescription(description);
            task.setNote(note);
            task.setStatus(status);
            // if he couldn't find it on the file that mean the task doesn't exist
            lock.writeLock().lock();
            fileHandler.UpdateFile(taskId, task);
            lock.writeLock().unlock();
        }

    }


}
