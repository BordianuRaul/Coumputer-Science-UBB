package Utils;

import java.util.*;

public class MyStack<T> implements MyIStack{

    private Stack<T> stack;

    public MyStack()
    {
        stack = new Stack<T>();
    }

    @Override
    public Object pop() {
        return stack.pop();
    }

    @Override
    public void push(Object v) {

    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<T> reverse() {
        List<T> items;
        items = Arrays.asList((T[])stack.toArray());

        Collections.reverse(items);
        return items;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "stack=" + stack +
                '}';
    }
}
