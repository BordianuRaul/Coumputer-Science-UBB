package model.Expressions;

import model.Exceptions.MyException;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;

public class VariableExpression implements InterfaceExpression{

    String id;
    public VariableExpression(String id) {this.id = id;}
    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable) throws MyException {
        return symbolTable.lookup(id);
    }

    @Override
    public String toString()
    {
        return id;
    }
}
