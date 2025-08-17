package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;


public class VariableDeclarationStatement implements InterfaceStatement {

    private String symbolName;
    private InterfaceType type;

    public VariableDeclarationStatement(String symbolName, InterfaceType type) {
        this.symbolName = symbolName;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        state.getSymbolTable().put(symbolName, type.defaultValue());
        return null;
    }

    @Override
    public String toString()
    {
        return type.toString() + " " + symbolName ;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        typeEnv.put(symbolName, type);
        return typeEnv;
    }
}
