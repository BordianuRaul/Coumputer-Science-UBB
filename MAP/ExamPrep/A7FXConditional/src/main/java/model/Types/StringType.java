package model.Types;

import model.Values.InterfaceValue;
import model.Values.StringValue;

public class StringType implements InterfaceType{

    public StringType()
    {

    }

    @Override
    public boolean equals(Object another){
        return another instanceof StringType;
    }

    @Override
    public String toString()
    {
        return "string";
    }
    @Override
    public InterfaceValue defaultValue() {
        return new StringValue("");
    }
}
