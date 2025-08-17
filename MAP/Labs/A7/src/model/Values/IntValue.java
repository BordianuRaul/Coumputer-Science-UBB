package model.Values;

import model.Types.IntType;
import model.Types.InterfaceType;

public class IntValue implements InterfaceValue {

    private int value;

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
    public InterfaceType getType() {
        return new IntType();
    }

    @Override
    public boolean equal(InterfaceValue otherValue) {

        if(otherValue instanceof IntValue)
        {
            IntValue convertedValue = (IntValue) otherValue;
            return this.value == convertedValue.getValue();
        }

        return false;
    }
}
