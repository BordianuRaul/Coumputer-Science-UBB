package model.Expressions;

import model.Exceptions.MyException;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;

public class ValueExpression implements InterfaceExpression{

    InterfaceValue value;

    public ValueExpression(InterfaceValue value)
    {
        this.value = value;
    }

    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable) throws MyException {
        return value;
    }

    @Override
    public String toString()
    {
        return value.toString();
    }
}
