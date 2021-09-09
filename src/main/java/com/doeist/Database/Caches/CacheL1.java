package com.doeist.Database.Caches;

import com.doeist.Database.FileHandler;

import java.util.concurrent.ConcurrentHashMap;

public class CacheL1<T> {


    // TODO: ٣١/٠٨/٢٠٢١  don't forget to put reeinternt lock for rac condition
    // TODO: ٣١/٠٨/٢٠٢١ don't forget to handle dead lock
    private static   ConcurrentHashMap<Integer, Node> entityMap;
    private static int count;
    private static   Node head, tail;
    private static int key;
   private static FileHandler fileHandler;

    private static final int CACHE_SIZE = 3;

    private static   CacheL1<Object> entityCache;

    /**
     * this class represent LRU cache with the following operations
     * get / set / delete/ Update / exit
     */
    public static  class Node {
        public int key;
        public Object value;
        Node pre;
        Node next;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    private CacheL1() {
    }

    public static CacheL1<Object> getInstance() {
        if (entityCache == null) {
            entityCache = new CacheL1<Object>();
            entityMap = new ConcurrentHashMap<>();
            head = new Node(0, null);
            tail = new Node(0, null);
            head.next = tail;
            tail.pre = head;
            head.pre = null;
            tail.next = null;
            key=0;
            count = 0;
            // return an instance of the TasksDatabase
           fileHandler = fileHandler.getInstance();

        }
        return entityCache;
    }


    /**
     * get the task from the LRU cache if not found load it from file
     *
     * @param key which represent the Task ID
     * @return it return the task that he found either from file or cache
     */

    public Object getFromCache(int key ,String table) {

        if (!(key>this.key)) {
            if (entityMap.get(key) != null) {
                Node node = entityMap.get(key);
                Object result = node.value;
                deleteNode(node);
                addToHead(node);
                return result;
            }
            System.out.println("we should not be heere");

            return fileHandler.loadFromFile(key, table);
        }
        return null;
    }

    // Delete Node in LRU TasksCache
    public void deleteNode(Node node) {
        synchronized (this) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
    }

    // put most recently used task first
    public void addToHead(Node node) {
        synchronized (this) {
            node.next = head.next;
            node.next.pre = node;
            node.pre = head;
            head.next = node;
        }
    }


    // put the task in LRU cache if not put it in the file
    public void setIntoCache( T value) {
        key++;

        if (entityMap.get(key) != null) {
            // refresh the doubly linked list so that MRU task is in head
            System.out.println("ksfjd");
            Node node = entityMap.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            System.out.println("setIntoCache key " +key);
            Node node = new Node(key, value);
            entityMap.put(key, node);
            synchronized (this) {
                fileHandler.saveToFile(key,entityMap.get(key).value);
            }
            if (count < CACHE_SIZE) {
                synchronized (this) {
                    count++;
                }
            } else {
                entityMap.remove(tail.pre.key);
                deleteNode(tail.pre);
            }
            addToHead(node);
        }

    }


    public static ConcurrentHashMap<Integer,Node> getEntitiesMap(){
        return entityMap;
    }


    public int getKey() {
        return key;
    }
}
