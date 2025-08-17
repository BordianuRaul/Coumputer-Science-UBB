package model.Values;

import model.Types.BoolType;
import model.Types.InterfaceType;

public class BoolValue implements InterfaceValue {

    private boolean value;

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
    public InterfaceType getType() {
        return new BoolType();
    }

    @Override
    public boolean equal(InterfaceValue otherValue) {

        if (otherValue instanceof BoolValue) {

            BoolValue convertedValue = (BoolValue) otherValue;
            return this.value == convertedValue.getValue();
        }
        return false;
    }
}
