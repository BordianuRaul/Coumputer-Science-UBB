package model.Statements;

import controller.MyException;
import model.Expressions.InterfaceExpression;
import model.PrgState;
import model.Type;
import model.Value;
import utils.MyIDictionary;
import utils.MyIStack;

import java.beans.Expression;

public class AssignStatement implements IStmt {

    String id;
    InterfaceExpression expression;

    public AssignStatement(String id, InterfaceExpression expression)
    {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> stack = state.getExeStack();
        MyIDictionary<String, Value> symbolTable = state.getSymTable();

        if(symbolTable.isDefined(id))
        {
            Value value = expression.evaluate(symbolTable);
            Type typeID = symbolTable.lookup(id).getType();

            if(value.getType().equals(typeID))
            {
                symbolTable.update(id, value);
            }
            else {
                throw new MyException("declared type of variable " + id +" and type of the assigned expression do not match.\n");
            }
        }
        else throw new MyException("the used variable " + id + " was not declared before");

        return state;

    }

    @Override
    public String toString()
    {
        return id + "=" + expression.toString();
    }

}
