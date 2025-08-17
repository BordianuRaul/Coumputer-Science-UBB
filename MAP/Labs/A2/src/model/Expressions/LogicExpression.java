package model.Expressions;

import controller.MyException;
import model.Value;
import utils.MyIDictionary;

public class LogicExpression implements InterfaceExpression{
    private InterfaceExpression expression1;
    private InterfaceExpression expression2;
    private int operationType;
    @Override
    public Value evaluate(MyIDictionary<String, Value> symbolTable) throws MyException {
        return null;
    }
    @Override
    public String toString()
    {
        return null;
    }
}
