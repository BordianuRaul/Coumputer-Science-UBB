package Utils;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K,V> implements MyIDictionary<K,V>{

    private Map<K,V> map;

    public MyDictionary()
    {
        map = new HashMap<>();
    }

    @Override
    public Object lookUp(Object key) {
        return map.get(key);
    }

    @Override
    public boolean isDefined(Object key) {
        return map.get(key) != null;
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public void update(Object key, Object value) {
        map.put(key, value);
    }
}
