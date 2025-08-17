package utils;
import java.util.List;
import java.util.Stack;

public interface InterfaceMyStack<T>{
    T pop();
    void push(T v);
    boolean isEmpty();
    List<T> reverse();
    public Stack<T> getStack();
}
