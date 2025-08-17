package model;

public class VarDeclStmt implements IStmt {

    private String symbolName;
    private Type type;

    public VarDeclStmt(String symbolName, Type type) {
        this.symbolName = symbolName;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) {
        return state;
    }
}
