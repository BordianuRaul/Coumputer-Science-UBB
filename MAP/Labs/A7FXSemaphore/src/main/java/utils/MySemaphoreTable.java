package utils;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;

public class MySemaphoreTable implements InterfaceSemaphoreTable{
    private HashMap<Integer,Pair<Integer, List<Integer>>> semaphoreTable;
    private int freeLocation = 0;

    public MySemaphoreTable() {
        semaphoreTable = new HashMap<Integer,Pair<Integer, List<Integer>>>();
    }
    public MySemaphoreTable(HashMap<Integer, Pair<Integer, List<Integer>>> lhsSemaphoreTable){
        semaphoreTable = lhsSemaphoreTable;}

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer key: semaphoreTable.keySet())
            stringBuilder.append(key.toString()).append("->").append(semaphoreTable.get(key).toString()).append(" ");
        return stringBuilder.toString();
    }

    @Override
    public void put(Integer key, Pair<Integer, List<Integer>> value) {
        semaphoreTable.put(key,value);
    }


    @Override
    public boolean lookup(Integer key) {
        return semaphoreTable.containsKey(key);
    }

    @Override
    public Pair<Integer, List<Integer>> get(Integer key) {
        return semaphoreTable.get(key);
    }

    @Override
    public HashMap<Integer, Pair<Integer, List<Integer>>> getMap() {
        return semaphoreTable;
    }

    @Override
    public int getFreeAddress(){
        freeLocation++;
        return freeLocation;
    }
    @Override
    public void update(int key,Pair<Integer, List<Integer>> value){
        semaphoreTable.replace(key,value);
    }

}
