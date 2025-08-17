package utils;

import java.util.HashMap;

public class MyLatchTable implements InterfaceLatchTable{
    private HashMap<Integer, Integer> latchTable;
    private int freeLocation = 0;

    public MyLatchTable() {
        latchTable = new HashMap<Integer, Integer>();
    }

    public MyLatchTable(HashMap<Integer, Integer> heap) {
        latchTable = heap;
    }

    @Override
    public void put(int key,int value){
        latchTable.put(key,value);
    }

    @Override
    public int lookUp(int key){
        return latchTable.get(key);
    }

    @Override
    public boolean containsKey(int key){
        return latchTable.containsKey(key);
    }

    @Override
    public int getFreeAddress(){
        freeLocation++;
        return freeLocation;
    }

    @Override
    public void update(int key,int value){
        latchTable.replace(key,value);
    }

    @Override
    public void setFreeAddress(int free){
        freeLocation = free;
    }

    @Override
    public HashMap<Integer, Integer> getLatchTable(){
        return latchTable;
    }

    @Override
    public void setLatchTable(HashMap<Integer, Integer> heap){
        latchTable = heap;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer key:latchTable.keySet())
            stringBuilder.append(key.toString() + "->" + latchTable.get(key).toString() + " ");
        return stringBuilder.toString();
    }
}
