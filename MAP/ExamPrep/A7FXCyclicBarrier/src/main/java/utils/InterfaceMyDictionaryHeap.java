package utils;

import model.Exceptions.MyException;

import java.util.Map;

public interface InterfaceMyDictionaryHeap<V> {
    int put (V v) throws MyException;
    void setContent(Map<Integer, V> map);
    Map<Integer,V> getContent();
    public boolean isDefined(Integer key);
    public V lookUp(Integer key);
    public void update(Integer Key, V value);
}
