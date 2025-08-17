package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.InterfaceType;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyStack;

public class AssignStatement implements InterfaceStatement {

    String id;
    InterfaceExpression expression;

    public AssignStatement(String id, InterfaceExpression expression)
    {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        InterfaceMyStack<InterfaceStatement> stack = state.getExecutionStack();
        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();

        if(symbolTable.isDefined(id))
        {
            InterfaceValue value = expression.evaluate(symbolTable, state.getHeap());
            InterfaceType typeID = symbolTable.lookup(id).getType();

            if(value.getType().equals(typeID))
            {
                symbolTable.update(id, value);
            }
            else {
                throw new MyException("declared type of variable " + id +" and type of the assigned expression do not match.\n");
            }
        }
        else throw new MyException("the used variable " + id + " was not declared before");

        return null;

    }

    @Override
    public String toString()
    {
        return id + "=" + expression.toString();
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType typeVariable = typeEnv.lookup(id);
        InterfaceType typeExpression = expression.typeCheck(typeEnv);
        if(typeVariable.equals(typeExpression))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types");
    }

}
