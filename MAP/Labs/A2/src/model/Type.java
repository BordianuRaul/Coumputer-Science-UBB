package model;

public interface Type {

    boolean equals(Object another);

    String toString();

    public Value defaultValue();
}
