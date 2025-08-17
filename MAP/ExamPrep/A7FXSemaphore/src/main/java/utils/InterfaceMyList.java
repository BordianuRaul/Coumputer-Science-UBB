package utils;

import java.util.ArrayList;

public interface InterfaceMyList<T> {

    void add(T e);

    void clear();

    public ArrayList<T> getList();
}
