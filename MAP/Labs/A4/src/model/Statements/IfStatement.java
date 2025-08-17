package model.Statements;

import model.Exceptions.MyException;
import model.Values.BoolValue;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Values.InterfaceValue;
import utils.InterfaceMyStack;

public class IfStatement implements InterfaceStatement {

    InterfaceExpression expression;
    InterfaceStatement thenBranch;
    InterfaceStatement elseBranch;

    public IfStatement(InterfaceExpression expression, InterfaceStatement thenBranch, InterfaceStatement elseBranch) {
        this.expression = expression;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public String toString() {
        return "if(" + expression.toString() + ") then " + thenBranch.toString() + " else " + elseBranch.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();
        InterfaceValue value;

        try {
            value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        } catch (Exception e) {
            throw new MyException(e.getMessage());
        }

        if (value instanceof BoolValue) {
            if (((BoolValue) value).getValue()) {
                stack.push(thenBranch);
            } else {
                stack.push(elseBranch);
            }
        } else {
            throw new MyException("The condition in the if statement is not a boolean expression.");
        }

        return state;
    }

}

