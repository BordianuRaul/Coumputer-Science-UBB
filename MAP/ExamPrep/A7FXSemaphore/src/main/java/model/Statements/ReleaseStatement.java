package model.Statements;

import javafx.util.Pair;
import model.Exceptions.MyException;
import model.ProgramState;
import model.Types.IntType;
import model.Types.InterfaceType;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceSemaphoreTable;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReleaseStatement implements InterfaceStatement{
    private String var;
    private static Lock lock = new ReentrantLock();

    public ReleaseStatement(String v){var=v;}

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();

        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();
        InterfaceSemaphoreTable semaphoreTable = state.getSemaphoreTable();
        try {
            if(!symbolTable.isDefined(var))throw new MyException("Index not in SymTable");
            if(!symbolTable.lookup(var).getType().equals(new IntType()))throw new MyException("Index should be int");
            IntValue foundValue = (IntValue)symbolTable.lookup(var);
            int foundIndex=foundValue.getValue();

            if(!semaphoreTable.lookup(foundIndex)) throw new MyException("Index not in SemaphoreTable");

            Pair<Integer, List<Integer>> foundSemaphore = semaphoreTable.get(foundIndex);

            if(foundSemaphore.getValue().contains(state.getCurrentID()))
                foundSemaphore.getValue().remove((Integer) state.getCurrentID());
            semaphoreTable.put(foundIndex, new Pair<>(foundSemaphore.getKey(),foundSemaphore.getValue()));

        }catch(Exception e){
            throw new MyException(e.getMessage());

        }
        lock.unlock();
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
        return "release(" + var + ")";
    }
}
