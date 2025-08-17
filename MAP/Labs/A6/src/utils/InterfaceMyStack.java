package utils;
import java.util.List;
public interface InterfaceMyStack<T>{
    T pop();
    void push(T v);
    boolean isEmpty();
    List<T> reverse();
}
