package model.Types;

import model.Values.IntValue;
import model.Values.InterfaceValue;

public class IntType implements InterfaceType {

    public IntType() {
    }
    @Override
    public boolean equals(Object another)
    {
        return another instanceof IntType;
    }


    @Override
    public String toString() {
        return "int";
    }

    @Override
    public InterfaceValue defaultValue() {
        return new IntValue(0);
    }
}
