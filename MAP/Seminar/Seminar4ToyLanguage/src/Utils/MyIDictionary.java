package Utils;

public interface MyIDictionary<K,V> {
    K lookUp(K key);

    boolean isDefined(K key);

    void put(K key, V value);

    void update(K key, V value);
}
