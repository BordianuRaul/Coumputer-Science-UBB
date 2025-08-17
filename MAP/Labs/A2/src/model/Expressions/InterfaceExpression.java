package model.Expressions;

import controller.MyException;
import model.Value;
import utils.MyIDictionary;

public interface InterfaceExpression {

    public Value evaluate(MyIDictionary<String, Value> symbolTable) throws MyException;

    public String toString();

}
