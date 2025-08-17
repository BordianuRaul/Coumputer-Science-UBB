package model.Statements;

import model.Exceptions.MyException;
import model.ProgramState;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;
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
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        return tail.typeCheck(head.typeCheck(typeEnv));
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();
        stack.push(tail);
        stack.push(head);
        return null;
    }
}
