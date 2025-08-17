package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.ReferenceType;
import model.Values.InterfaceValue;
import model.Values.ReferenceValue;
import utils.InterfaceMyDictionary;

import java.io.IOException;

public class New implements InterfaceStatement{
    String variableName;
    InterfaceExpression expression;

    public New(String name, InterfaceExpression expression){
        this.variableName = name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {

        if(!state.getSymbolTable().isDefined(variableName))
            throw new MyException("The variable is not defined in the symbol table!.\n");

        InterfaceValue stateValue = state.getSymbolTable().lookup(variableName);

        if(!(stateValue.getType() instanceof ReferenceType))
            throw new MyException("The variable with this name from the symbol table is not of reference type!\n");

        InterfaceValue value = expression.evaluate(state.getSymbolTable(), state.getHeap());

        if(!((ReferenceType)(stateValue).getType()).getInner().equals(value.getType()))
            throw new MyException("The value from the state and the value from the heap allocation are not of the same type!\n");

       int address = state.getHeap().put(value);

        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();
        symbolTable.put(variableName, new ReferenceValue(address, value.getType()));

        state.setSymTable(symbolTable);

        return null;
    }
    @Override
    public String toString() {
        return "new(" + variableName + "," + expression.toString() + ")";
    }
}
