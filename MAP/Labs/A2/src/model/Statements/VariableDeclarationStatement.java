package model.Statements;

import controller.MyException;
import model.PrgState;
import model.Type;

public class VariableDeclarationStatement implements IStmt{

    String name;
    Type type;

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }
}
