package model.Expressions;

import controller.MyException;
import model.Value;
import utils.MyIDictionary;

public class VariableExpression implements InterfaceExpression{

    String id;
    public VariableExpression(String id) {this.id = id;}
    @Override
    public Value evaluate(MyIDictionary<String, Value> symbolTable) throws MyException {
        return symbolTable.lookup(id);
    }

    @Override
    public String toString()
    {
        return id;
    }
}
