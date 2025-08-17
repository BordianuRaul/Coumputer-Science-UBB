package utils;

import javafx.util.Pair;
import model.Exceptions.MyException;

import java.util.HashMap;
import java.util.List;

public interface InterfaceSemaphoreTable {

    String toString();
    void put(Integer key, Pair<Integer, List<Integer>> value);
    boolean lookup(Integer key);
    Pair<Integer,List<Integer>> get(Integer key);
    HashMap<Integer,Pair<Integer, List<Integer>>> getMap();
    int getFreeAddress();
    void update(int key,Pair<Integer, List<Integer>> value);
}
