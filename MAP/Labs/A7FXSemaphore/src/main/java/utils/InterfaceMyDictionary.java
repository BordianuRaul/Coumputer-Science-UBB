package utils;

import java.util.Map;
import java.util.Set;

public interface InterfaceMyDictionary<K,V> {
    V lookup(K key);
    boolean isDefined(K key);
    void put(K key,V value);
    void update(K key, V value);
    public Set<K> getKeys();
    void delete(K key);
    public Map<K, V> getContent();
    public InterfaceMyDictionary<K, V> cloneDictionary();
}
