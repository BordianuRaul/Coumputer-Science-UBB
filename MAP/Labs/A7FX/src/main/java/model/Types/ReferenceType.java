package model.Types;

import model.Values.InterfaceValue;
import model.Values.ReferenceValue;

public class ReferenceType implements InterfaceType{
    private InterfaceType inner;

    public ReferenceType(InterfaceType inner){
        this.inner = inner;
    }
    public InterfaceType getInner(){
        return this.inner;
    }

    public boolean equals(Object another){
        if (another instanceof ReferenceType)
            return this.inner.equals(((ReferenceType) another).getInner());
        else
            return false;
    }

    public String toString(){
        return inner.toString() + " reference";
    }
    @Override
    public InterfaceValue defaultValue() {
        return new ReferenceValue(0, this.inner);
    }
}
