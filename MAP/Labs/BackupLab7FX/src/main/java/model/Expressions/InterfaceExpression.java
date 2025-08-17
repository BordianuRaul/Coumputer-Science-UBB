package model.Expressions;

import model.Exceptions.MyException;
import model.Types.InterfaceType;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

public interface InterfaceExpression {

    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable, InterfaceMyDictionaryHeap<InterfaceValue> heap) throws MyException;

    public String toString();

    InterfaceType typeCheck(InterfaceMyDictionary<String,InterfaceType> typeEnv) throws MyException;

}
