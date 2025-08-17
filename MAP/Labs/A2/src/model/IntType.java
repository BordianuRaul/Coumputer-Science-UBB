package model;

public class IntType implements Type {

    public IntType() {
    }

    public boolean equals(Object another)
    {
        return another instanceof IntType;
    }


    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}
