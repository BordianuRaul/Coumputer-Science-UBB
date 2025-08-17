package model;

public class BoolValue implements Value{

    boolean value;

    public BoolValue(boolean value){
        this.value = value;
    }

    public String toString(){
        return Boolean.toString(value);
    }

    public boolean getValue(){
        return value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }
}
