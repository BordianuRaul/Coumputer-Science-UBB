package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Values.InterfaceValue;

public class PrintStatement implements InterfaceStatement {

    InterfaceExpression expression;

    public PrintStatement(InterfaceExpression expression) { this.expression = expression;}

    public String toString(){

        return "print( " + expression.toString() + " )";

    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        InterfaceValue value = expression.evaluate(state.getSymbolTable());
        state.getOut().add(value);
        return state;

    }
}
