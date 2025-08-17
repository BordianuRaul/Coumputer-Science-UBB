package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K,V> implements InterfaceMyDictionary<K,V> {

    private Map<K,V> map;

    public MyDictionary(){
        map = new HashMap<>();
    }

    @Override
    public V lookup(K key) {

        return map.get(key);
    }
    @Override
    public boolean isDefined(K key) {
        return map.get(key)!=null;
    }
    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }
    @Override
    public void update(K key, V value) {
        map.put(key, value);
    }
    public Set<K> getKeys()
    {
        return this.map.keySet();
    }
    @Override
    public void delete(K key) {
        this.map.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder stringMap = new StringBuilder();
        for(K key : this.getKeys())
            stringMap.append(key.toString()).append(" -> ").append(this.map.get(key).toString()).append("\n");

        return stringMap.toString();
    }
    @Override
    public Map<K, V> getContent(){return this.map;};

    public InterfaceMyDictionary<K, V> cloneDictionary(){
        InterfaceMyDictionary<K, V> clonedDictionary = new MyDictionary<>();
        for(K key : this.map.keySet())
            clonedDictionary.put(key, map.get(key));
        return clonedDictionary;
    }
}
