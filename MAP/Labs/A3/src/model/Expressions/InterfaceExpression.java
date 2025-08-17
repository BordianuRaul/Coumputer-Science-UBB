package model.Expressions;

import model.Exceptions.MyException;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;

public interface InterfaceExpression {

    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable) throws MyException;

    public String toString();

}
