package model.Statements;

import javafx.util.Pair;
import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.IntType;
import model.Types.InterfaceType;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;
import utils.InterfaceSemaphoreTable;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CreateSemaphoreStatement implements InterfaceStatement{
    private String var;
    private InterfaceExpression expression;
    private static Lock lock = new ReentrantLock();

    public CreateSemaphoreStatement(String var,InterfaceExpression expression){
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();

        InterfaceMyDictionary<String, InterfaceValue> table = state.getSymbolTable();
        InterfaceMyDictionaryHeap<InterfaceValue> heap = state.getHeap();
        InterfaceSemaphoreTable semaphoreTable = state.getSemaphoreTable();

        try {
            IntValue nr1 = (IntValue)(expression.evaluate(table, heap));

            int n1=(nr1).getValue();
            int f=semaphoreTable.getFreeAddress();

            semaphoreTable.put(f, new Pair<>(n1,new ArrayList<>()));

            if(table.isDefined(var) && table.lookup(var).getType().equals(new IntType()))
                table.update(var,new IntValue(f));
            else throw new MyException("var does not exist in SymTable");

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
        return "create(" + var + "," + expression.toString()+ ")";
    }
}
