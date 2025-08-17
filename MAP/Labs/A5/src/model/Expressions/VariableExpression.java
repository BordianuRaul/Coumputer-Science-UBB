package model.Expressions;

import model.Exceptions.MyException;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

public class VariableExpression implements InterfaceExpression{

    String id;
    public VariableExpression(String id) {this.id = id;}
    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable, InterfaceMyDictionaryHeap<InterfaceValue> heap) throws MyException {
        return symbolTable.lookup(id);
    }

    @Override
    public String toString()
    {
        return id;
    }
}
