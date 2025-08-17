package model.Types;

import model.Values.BoolValue;
import model.Values.InterfaceValue;

public class BoolType implements InterfaceType {

    @Override
    public boolean equals(Object another)
    {
        return another instanceof BoolType;
    }

    @Override
    public String toString()
    {
        return "bool";
    }

    @Override
    public InterfaceValue defaultValue() {
        return new BoolValue(false);
    }

}
