package com.doeist.Database.Caches;

import com.doeist.Database.FileHandler;
import com.doeist.Model.Task.Task;

import java.util.concurrent.ConcurrentHashMap;

public class CacheL2 {

    private static CacheL1 cacheL1;
    private static FileHandler fileHandler;
    private static ConcurrentHashMap<Integer, CacheL1.Node> entityMap;
    private static int key;
    private static CacheL2 cacheL2;

    private CacheL2(){

    }

    public static CacheL2 getInstence(){
        if (cacheL2==null) {
            cacheL2=new CacheL2();
            cacheL1 = CacheL1.getInstance();
            fileHandler = FileHandler.getInstance();

            // might produce an error
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
        int key =cacheL1.getKey();
        if (!(taskId>key)) {
            System.out.println("heeeeeeeeeeeeeeee");
            synchronized (this) {
                if (entityMap.get(key) != null) {
                    cacheL1.deleteNode(entityMap.get(key));
                    entityMap.remove(key);
                }
                    fileHandler.DeleteFromFile(key);
            }
        }

    }

    /**
     * Update the task either from CacheL1 and File
     *
     * @param taskId represents ID of a task
     *
     */
    public void UpdateCache(int taskId, String description, String note, boolean status) {

        /*
         TODO: ٠١/٠٩/٢٠٢١ why key is not working this key is not the same
         TODO: ٠١/٠٩/٢٠٢١  as the CacheL1 key
        */
        if (!(taskId>cacheL1.getKey())){
                Task task = (Task)entityMap.get(taskId).value;
                task.setDescription(description);
                task.setNote(note);
                task.setStatus(status);
        // if he couldn't find it on the file that mean the task doesn't exist
            synchronized (this) {
                System.out.println("key in update cache"+taskId);
                fileHandler.UpdateFile(taskId,task);
            }
        }

    }


}
