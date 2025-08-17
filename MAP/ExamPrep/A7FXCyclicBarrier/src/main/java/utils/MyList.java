package utils;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements InterfaceMyList<T> {
    List<T> list;

    public MyList()
    {
        list = new ArrayList<>();
    }
    @Override
    public void add(T e) {
        list.add(e);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public String toString() {
        StringBuilder listConvertedToString = new StringBuilder();
        for(T element:list)
            listConvertedToString.append(element.toString()).append("\n");

        return listConvertedToString.toString();
    }
    @Override
    public ArrayList<T> getList() {
        return (ArrayList<T>) list;
    }
}
