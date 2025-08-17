package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.ValueExpression;
import model.ProgramState;
import model.Types.IntType;
import model.Types.InterfaceType;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceLatchTable;
import utils.InterfaceMyDictionary;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStatement implements InterfaceStatement{

    private String var;
    private static Lock lock = new ReentrantLock();

    public CountDownStatement(String var){this.var=var;}

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();

        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();
        InterfaceLatchTable latch = state.getLatchTable();

        if(!symbolTable.isDefined(var))throw new MyException("Variable not defined");

        IntValue fi = (IntValue)symbolTable.lookup(var);
        int foundIndex = fi.getValue();

        if(!latch.containsKey(foundIndex))throw new MyException("Index not in LatchTable");
        if(latch.lookUp(foundIndex)>=0) {
            latch.update(foundIndex, latch.lookUp(foundIndex) - 1);
            state.getExecutionStack().push(new PrintStatement(new ValueExpression(new IntValue(state.getCurrentID()))));
        }
        lock.unlock();;
        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        if(typeEnv.lookup(var).equals(new IntType()))
            return typeEnv;
        else throw new MyException("Var is not int");
    }

    @Override
    public String toString() {
        return "countDown("+var+")";
    }

}
