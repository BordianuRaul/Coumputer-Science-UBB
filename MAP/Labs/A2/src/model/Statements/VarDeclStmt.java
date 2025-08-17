package model.Statements;

import model.PrgState;
import model.Type;

public class VarDeclStmt implements IStmt {

    private String symbolName;
    private Type type;

    public VarDeclStmt(String symbolName, Type type) {
        this.symbolName = symbolName;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) {
        state.getSymTable().put(symbolName, type.defaultValue());
        return state;
    }

    @Override
    public String toString()
    {
        return type.toString() + " " + symbolName ;
    }
}
