package model.Statements;

import model.ProgramState;
import model.Types.InterfaceType;


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
        return state;
    }

    @Override
    public String toString()
    {
        return type.toString() + " " + symbolName ;
    }
}
