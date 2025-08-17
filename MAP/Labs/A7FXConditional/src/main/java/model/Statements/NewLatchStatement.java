package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.IntType;
import model.Types.InterfaceType;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceLatchTable;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStatement implements InterfaceStatement{
    private String var;
    private InterfaceExpression expression;
    private static Lock lock = new ReentrantLock();

    public NewLatchStatement(String var,InterfaceExpression expression){
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();
        try{
            InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();
            InterfaceMyDictionaryHeap<InterfaceValue> heap = state.getHeap();
            InterfaceLatchTable latch = state.getLatchTable();

            IntValue nr = (IntValue)(expression.evaluate(symbolTable, heap));
            int number = nr.getValue();

            int freeLoc= latch.getFreeAddress();
            latch.put(freeLoc,number);

            if(symbolTable.isDefined(var))
                symbolTable.update(var,new IntValue(freeLoc));
            else
                symbolTable.put(var,new IntValue(freeLoc));

        } catch(MyException e){
            throw new MyException(e.getMessage());
        }
        lock.unlock();
        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        if(typeEnv.lookup(var).equals(new IntType()))
            if(expression.typeCheck(typeEnv).equals(new IntType()))
                return typeEnv;
            else throw new MyException("Expression is not int");
        else throw new MyException("Var is not int");
    }

    @Override
    public String toString() {
        return "newLatch(" + var + "," + expression.toString() + ")";
    }
}
