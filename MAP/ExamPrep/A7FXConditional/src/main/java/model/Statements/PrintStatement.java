package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.InterfaceType;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;

public class PrintStatement implements InterfaceStatement {

    InterfaceExpression expression;

    public PrintStatement(InterfaceExpression expression) { this.expression = expression;}

    public String toString(){

        return "print( " + expression.toString() + " )";

    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        InterfaceValue value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        state.addOut(value);
        return null;

    }
}
