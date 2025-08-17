package model;

public class CompStmt implements IStmt {

    private IStmt head;
    private IStmt tail;

    public CompStmt(IStmt head, IStmt tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public PrgState execute(PrgState state) {
        return state;
    }
}
