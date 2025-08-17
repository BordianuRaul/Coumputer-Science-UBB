package model.Expressions;

import model.Exceptions.MyException;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

public class LogicExpression implements InterfaceExpression{
    private InterfaceExpression expression1;
    private InterfaceExpression expression2;
    private int operationType;
    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable, InterfaceMyDictionaryHeap<InterfaceValue> heap) throws MyException {
        return null;
    }
    @Override
    public String toString()
    {
        return null;
    }
}
