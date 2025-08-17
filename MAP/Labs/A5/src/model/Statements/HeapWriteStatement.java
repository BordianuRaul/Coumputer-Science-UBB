package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.ReferenceType;
import model.Values.InterfaceValue;
import model.Values.ReferenceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

import java.io.IOException;

public class HeapWriteStatement implements InterfaceStatement{

    String variableName;
    InterfaceExpression expression;

    public HeapWriteStatement(String variableName, InterfaceExpression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {

        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();
        InterfaceMyDictionaryHeap<InterfaceValue> heap = state.getHeap();

        if(!symbolTable.isDefined(variableName))
            throw new MyException("The variable is not defined in the symbol table.\n");

        if(!(symbolTable.lookup(variableName).getType() instanceof ReferenceType))
            throw new MyException("The type of the variable is not of reference type.\n");

        ReferenceValue value  = (ReferenceValue) symbolTable.lookup(variableName);

        if(!heap.isDefined(value.getAddress()))
            throw new MyException("The address of the reference values is not defined in the heap.\n");

        if(!heap.lookUp(value.getAddress()).getType().equals(expression.evaluate(symbolTable, heap).getType()))
            throw new MyException("The type of the " + variableName + "in the symbol table is not the same with the type resulted from the evaluation of the expression.\n");

        heap.update(value.getAddress(), expression.evaluate(symbolTable, heap));

        state.setHeap(heap);

        return null;
    }

    @Override
    public String toString(){
        return "write heap(" + this.expression.toString() + ")";
    }


}
