package model.Statements;

import model.Exceptions.MyException;
import model.Types.BoolType;
import model.Types.InterfaceType;
import model.Values.BoolValue;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
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
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType typeExpression = expression.typeCheck(typeEnv);
        if(typeExpression.equals(new BoolType())){
            thenBranch.typeCheck(typeEnv.cloneDictionary());
            elseBranch.typeCheck(typeEnv.cloneDictionary());
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
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

        return null;
    }

}

