package com.doeist.Database.Caches;

import com.doeist.Database.FileHandler;
import com.doeist.Database.IdGenerator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheL1<T> {

    private static ConcurrentHashMap<Integer, Node> entityMap;

    private static FileHandler fileHandler;

    private static ReentrantReadWriteLock lock;
    private static int count;
    private static Node head, tail;
    private static int key;
    private static IdGenerator idGenerator;
    private static final int CACHE_SIZE = 3;


    private static CacheL1<Object> entityCache;

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
            key = 0;
            idGenerator=IdGenerator.getIdInstance();
            count = 0;
            lock=new ReentrantReadWriteLock();
            
            // return an instance of the TasksDatabase
            fileHandler = fileHandler.getInstance();

        }
        return entityCache;
    }

    /**
     * this class represent LRU cache with the following operations
     * get / set / delete/ Update / exit
     */
    public static class Node {
        public int key;
        public Object value;
        Node pre;
        Node next;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }


    /**
     * get the task from the LRU cache if not found load it from file
     *
     * @param key which represent the Task ID
     * @return it return the task that he found either from file or cache
     */

    public Object getFromCache(int key, String table) {

        lock.readLock().lock();
        if (idGenerator.isReserved(key)) {
            if (entityMap.get(key) != null) {
                Node node = entityMap.get(key);
                Object result = node.value;
                deleteNode(node);
                addToHead(node);
                lock.readLock().unlock();
                return result;
            }
            Object o=fileHandler.loadFromFile(key, table);
            lock.readLock().unlock();
            return o;
        }
        lock.readLock().unlock();
        return null;
    }

    // Delete Node in LRU TasksCache
    public void deleteNode(Node node) {
        synchronized (this) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
    }

    // put most recently used entity first
    public void addToHead(Node node) {
        synchronized (this) {
            node.next = head.next;
            node.next.pre = node;
            node.pre = head;
            head.next = node;
        }
    }


    // put the task in LRU cache if not put it in the file
    public void setIntoCache(T value) {
        key= idGenerator.generateKey();
        if (entityMap.get(key) != null) {
            // refresh the doubly linked list so that MRU task is in head
            Node node = entityMap.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value);
            entityMap.put(key, node);
            lock.writeLock().lock();
            fileHandler.saveToFile(key, entityMap.get(key).value);
            if (count < CACHE_SIZE) {
                synchronized (this) {
                    count++;
                }
            } else {
                entityMap.remove(tail.pre.key);
                deleteNode(tail.pre);
            }
            lock.writeLock().unlock();
            addToHead(node);
        }

    }


    public static ConcurrentHashMap<Integer, Node> getEntitiesMap() {
        return entityMap;
    }

}
