package utils;

import model.Exceptions.MyException;
import model.Types.InterfaceType;
import model.Values.InterfaceValue;

import java.util.HashMap;
import java.util.Map;


public class MyDictionaryHeap<V> implements InterfaceMyDictionaryHeap<V>{

    private static int nextFreeAddress = 0;
    protected Map<Integer, V> map;

    public MyDictionaryHeap(){
        this.map = new HashMap<Integer, V>();
    }
    private int nextFreeAddress(){
        nextFreeAddress++;
        return nextFreeAddress;

    }
    @Override
    public int put(V newValue) throws MyException {
        map.put(this.nextFreeAddress(), newValue);
        return nextFreeAddress;
    }
    @Override
    public void setContent(Map<Integer, V> map) {
        this.map = map;
    }
    @Override
    public Map<Integer, V> getContent() {
        synchronized (this) {
            return map;
        }
    }

    @Override
    public String toString(){
        return map.toString();
    }

    @Override
    public boolean isDefined(Integer key){return this.map.get(key) != null;}
    @Override
    public V lookUp(Integer key){return this.map.get(key);}
    @Override
    public void update(Integer key, V value){this.map.put(key, value);}

}
