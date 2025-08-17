package model.Statements;

import javafx.util.Pair;
import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.IntType;
import model.Types.InterfaceType;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceBarrierTable;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements InterfaceStatement{

    private final String var;
    private final InterfaceExpression expression;
    private static final Lock lock = new ReentrantLock();

    public NewBarrierStatement(String var, InterfaceExpression expression) {
        this.var = var;
        this.expression = expression;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();
        InterfaceMyDictionary<String, InterfaceValue> symTable = state.getSymbolTable();
        InterfaceMyDictionaryHeap heap = state.getHeap();
        InterfaceBarrierTable barrierTable = state.getBarrierTable();
        IntValue number = (IntValue) (expression.evaluate(symTable, heap));
        int nr = number.getValue();
        int freeAddress = barrierTable.getFreeAddress();
        barrierTable.put(freeAddress, new Pair<>(nr, new ArrayList<>()));
        if (symTable.isDefined(var))
            symTable.update(var, new IntValue(freeAddress));
        else
            throw new MyException(String.format("%s is not defined in the symbol table!", var));
        lock.unlock();
        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        if (typeEnv.lookup(var).equals(new IntType()))
            if (expression.typeCheck(typeEnv).equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("Expression is not of type int!");
        else
            throw new MyException("Variable is not of type int!");
    }

    @Override
    public String toString() {
        return String.format("newBarrier(%s, %s)", var, expression);
    }
}
