package model.Values;

import model.Types.InterfaceType;

public interface InterfaceValue {
    public String toString();
    public InterfaceType getType();
    public boolean equal(InterfaceValue otherValue);

}
