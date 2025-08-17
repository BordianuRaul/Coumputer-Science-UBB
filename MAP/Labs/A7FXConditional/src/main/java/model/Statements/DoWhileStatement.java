package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.BoolType;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;

public class DoWhileStatement implements InterfaceStatement{
    private InterfaceStatement stmt;
    private InterfaceExpression exp;

    public DoWhileStatement(InterfaceStatement s,InterfaceExpression e){stmt=s;exp=e;}

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        InterfaceStatement newDoWhile=new CompoundStatement(stmt,new WhileStatement(exp,stmt));
        state.getExecutionStack().push(newDoWhile);

        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType typexp=exp.typeCheck(typeEnv);
        if(typexp.equals(new BoolType())){
            stmt.typeCheck(typeEnv.cloneDictionary());
            return typeEnv;
        }
        else throw new MyException("Condition should be bool");
    }

    @Override
    public String toString() {
        return "do "+stmt.toString()+" while "+exp.toString();
    }
}
