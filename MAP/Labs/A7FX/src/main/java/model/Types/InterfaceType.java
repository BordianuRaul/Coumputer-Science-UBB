package model.Types;

import model.Values.InterfaceValue;

public interface InterfaceType {

    boolean equals(Object another);

    String toString();

    public InterfaceValue defaultValue();
}
