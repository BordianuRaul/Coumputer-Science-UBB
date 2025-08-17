package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.BoolType;
import model.Types.InterfaceType;
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
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType typexp=expression.typeCheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            statement.typeCheck(typeEnv.cloneDictionary());
            return typeEnv;
        }
        else
            throw new MyException("The condition of WHILE has not the type bool");
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
