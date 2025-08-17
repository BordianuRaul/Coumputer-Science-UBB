package model.Values;

import model.Types.InterfaceType;
import model.Types.StringType;

public class StringValue implements InterfaceValue{

    private String value;

    public StringValue(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public InterfaceType getType() {
        return new StringType();
    }

    @Override
    public boolean equal(InterfaceValue otherValue) {
        if(otherValue instanceof StringValue)
        {
            StringValue convertedValue = (StringValue) otherValue;
            return this.value.compareTo(convertedValue.getValue()) == 0;
        }
        return false;
    }

    @Override
    public String toString(){
        return " ' " + this.value + " ' ";
    }
}
