package model.Statements;

import controller.MyException;
import model.Expressions.InterfaceExpression;
import model.PrgState;
import model.Value;

import java.beans.Expression;

public class PrintStatement implements IStmt{

    InterfaceExpression expression;

    public PrintStatement(InterfaceExpression expression) { this.expression = expression;}

    public String toString(){

        return "print( " + expression.toString() + " )";

    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        Value value = expression.evaluate(state.getSymTable());
        state.getOut().add(value);
        return state;

    }
}
