package model.Values;

import model.Types.IntType;
import model.Types.InterfaceType;
import model.Types.ReferenceType;

public class ReferenceValue implements InterfaceValue{

    private int address;
    private InterfaceType locationType;

    public ReferenceValue(int address, InterfaceType locationType)
    {
        this.address = address;
        this.locationType = locationType;
    }

    public InterfaceType getLocationType() {
        return locationType;
    }

    public int getAddress(){
        return this.address;
    }

    @Override
    public boolean equal(InterfaceValue otherValue) {

        if(otherValue instanceof ReferenceValue){
            return this.address == ((ReferenceValue) otherValue).address;
        }

        return false;

    }
    @Override
    public String toString(){
        return Integer.toString(address) + " " + locationType.toString();
    }

    @Override
    public InterfaceType getType() {
        return new ReferenceType(locationType);
    }
}
