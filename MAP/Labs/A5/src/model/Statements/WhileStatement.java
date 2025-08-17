package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Values.BoolValue;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;
import utils.InterfaceMyStack;

import java.io.IOException;

public class WhileStatement implements InterfaceStatement{
    private InterfaceExpression expression;
    private InterfaceStatement statement;

    public WhileStatement(InterfaceExpression expression, InterfaceStatement statement){
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "while( " + expression.toString()  + ") " + statement.toString() + "\n";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {
        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();
        InterfaceMyDictionaryHeap<InterfaceValue> heap = state.getHeap();

        BoolValue expressionResult = (BoolValue) expression.evaluate(symbolTable, heap);

        if(expressionResult.getValue()){
            InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();

            stack.push(this);
            state.setExecutionStack(stack);
            statement.execute(state);
        }

        return null;
    }
}
