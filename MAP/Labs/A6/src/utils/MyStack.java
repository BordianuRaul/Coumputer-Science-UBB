package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements InterfaceMyStack<T> {
    private Stack<T> stack;

    public MyStack()
    {
        stack = new Stack<T>();
    }
    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
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

        StringBuilder stackConvertedToString = new StringBuilder();
        Stack<T> auxiliaryStack = (Stack<T>) this.stack.clone();

        while(!auxiliaryStack.isEmpty())
        {
            stackConvertedToString.append(auxiliaryStack.pop().toString()).append("\n");
        }

        return  stackConvertedToString.toString();
    }
}
