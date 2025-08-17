package model.Statements;

import controller.MyException;
import model.BoolValue;
import model.Expressions.InterfaceExpression;
import model.PrgState;
import model.Value;
import utils.MyIStack;

public class IfStatement implements IStmt {

    InterfaceExpression expression;
    IStmt thenBranch;
    IStmt elseBranch;

    public IfStatement(InterfaceExpression expression, IStmt thenBranch, IStmt elseBranch) {
        this.expression = expression;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public String toString() {
        return "if(" + expression.toString() + ") then " + thenBranch.toString() + " else " + elseBranch.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stack = state.getExeStack();
        Value value;

        try {
            value = expression.evaluate(state.getSymTable());
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

