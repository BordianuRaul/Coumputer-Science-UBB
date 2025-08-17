package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.Expressions.RelationalExpression;
import model.Expressions.VariableExpression;
import model.ProgramState;
import model.Types.IntType;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;

public class ForStatement implements InterfaceStatement{

    private String v;
    private InterfaceExpression exp1;
    private InterfaceExpression exp2;
    private InterfaceExpression exp3;
    private InterfaceStatement stmt;

    public ForStatement(String val,InterfaceExpression e1,InterfaceExpression e2,InterfaceExpression e3,InterfaceStatement s){
        v=val;exp1=e1;exp2=e2;exp3=e3;stmt=s;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        InterfaceStatement newFor=new CompoundStatement(new VariableDeclarationStatement(v,new IntType()),
                new CompoundStatement(new AssignStatement(v,exp1),new WhileStatement(new RelationalExpression(new VariableExpression(v),exp2,"<"),
                        new CompoundStatement(stmt,new AssignStatement("v",exp3)))));
        state.getExecutionStack().push(newFor);

        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        //we know for sure that v will be type int because we declare it in this for as an int, so we won't typeCheck it
        InterfaceType typexp1=exp1.typeCheck(typeEnv);
        InterfaceType typexp2=exp2.typeCheck(typeEnv);
        InterfaceType typexp3=exp3.typeCheck(typeEnv);

        if(typexp1.equals(new IntType()) && typexp2.equals(new IntType()) && typexp3.equals(new IntType())) return typeEnv;
        else throw new MyException("The for is invalid");
    }

    @Override
    public String toString() {
        return "for("+v+"="+exp1.toString()+";"+v+"<"+exp2.toString()+";"+v+"="+exp3.toString()+"){"+stmt.toString()+"}";
    }
}
