package model.Expressions;

import controller.MyException;
import model.Value;
import utils.MyIDictionary;

public class ValueExp implements InterfaceExpression{
    Value element;
    public ValueExp(Value value) {
        this.element = value;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> symbolTable) throws MyException {
        return element;
    }
    @Override
    public String toString()
    {
        return element.toString();
    }
}
