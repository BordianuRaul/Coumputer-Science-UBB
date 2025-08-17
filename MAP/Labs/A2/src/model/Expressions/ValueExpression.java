package model.Expressions;

import controller.MyException;
import model.Value;
import utils.MyIDictionary;

public class ValueExpression implements InterfaceExpression{

    Value value;

    public ValueExpression(Value value)
    {
        this.value = value;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symbolTable) throws MyException {
        return value;
    }

    @Override
    public String toString()
    {
        return value.toString();
    }
}
