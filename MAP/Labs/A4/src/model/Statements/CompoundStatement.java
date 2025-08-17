package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;
import utils.InterfaceMyStack;

public class CompoundStatement implements InterfaceStatement {

    private InterfaceStatement head;
    private InterfaceStatement tail;

    public CompoundStatement(InterfaceStatement head, InterfaceStatement tail) {
        this.head = head;
        this.tail = tail;
    }

    public String toString()
    {
        return head.toString() + ";" + tail.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();
        stack.push(tail);
        stack.push(head);
        return state;
    }
}
