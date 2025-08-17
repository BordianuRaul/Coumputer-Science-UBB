package model.Statements;

import controller.MyException;
import model.PrgState;
import utils.MyIStack;

public class CompStmt implements IStmt {

    private IStmt head;
    private IStmt tail;

    public CompStmt(IStmt head, IStmt tail) {
        this.head = head;
        this.tail = tail;
    }

    public String toString()
    {
        return head.toString() + ";" + tail.toString();
    }


    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stack = state.getExeStack();
        stack.push(tail);
        stack.push(head);
        return state;
    }
}
