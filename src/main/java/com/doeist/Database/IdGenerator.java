package com.doeist.Database;

import java.util.ArrayList;
import java.util.List;

public class IdGenerator {

    private static List<Integer> idList;

    private static IdGenerator idGenerator;

    private IdGenerator() {

    }

    public static IdGenerator getIdInstance() {
        if (idGenerator == null) {
            idGenerator = new IdGenerator();
            idList = new ArrayList<>();
        }
        return idGenerator;

    }


    public void putKey(int key) {
        idList.add(key);
    }


    public synchronized int generateKey() {
        int key = 0;

        while (idList.contains(key)) {
            key++;
        }
        putKey(key);
        return key;
    }


    public synchronized void deleteKey(int key) {
        idList.removeIf(integer -> integer == key);
    }

    public boolean isReserved(int key) {
        return idList.contains(key);
    }
}
