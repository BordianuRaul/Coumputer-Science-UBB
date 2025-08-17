package model;

public class IntValue implements Value{

    int value;

    public IntValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public String toString(){
        return Integer.toString(value);
    }

    @Override
    public Type getType() {
        return new IntType();
    }
}
