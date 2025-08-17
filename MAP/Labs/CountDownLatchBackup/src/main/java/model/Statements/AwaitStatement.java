package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;
import model.Types.IntType;
import model.Types.InterfaceType;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceLatchTable;
import utils.InterfaceMyDictionary;

public class AwaitStatement implements InterfaceStatement{
    private String var;

    public AwaitStatement(String v){var=v;}

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        InterfaceMyDictionary<String, InterfaceValue> table=state.getSymbolTable();
        InterfaceLatchTable latch = state.getLatchTable();

        if(!table.isDefined(var))throw new MyException("Variable not defined");

        IntValue fi = (IntValue)table.lookup(var);
        int foundIndex=fi.getValue();

        if(!latch.containsKey(foundIndex)) throw new MyException("Index not in LatchTable");
        if(latch.lookUp(foundIndex)!=0)
            state.getExecutionStack().push(this);

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
        return "await(" + var + ")";
    }
}
