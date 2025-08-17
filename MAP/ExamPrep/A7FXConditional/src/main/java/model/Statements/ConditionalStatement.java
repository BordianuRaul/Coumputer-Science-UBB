package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.Expressions.VariableExpression;
import model.ProgramState;
import model.Types.BoolType;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;

public class ConditionalStatement implements InterfaceStatement{
    private String v;
    private InterfaceExpression exp1;
    private InterfaceExpression exp2;
    private InterfaceExpression exp3;

    public ConditionalStatement(String val,InterfaceExpression e1,InterfaceExpression e2,InterfaceExpression e3){
        v=val;exp1=e1;exp2=e2;exp3=e3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        InterfaceStatement newIf=new IfStatement(exp1,new AssignStatement(v,exp2),new AssignStatement(v,exp3));
        state.getExecutionStack().push(newIf);
        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType typev=new VariableExpression(v).typeCheck(typeEnv);
        InterfaceType typexp1=exp1.typeCheck(typeEnv);
        InterfaceType typexp2=exp2.typeCheck(typeEnv);
        InterfaceType typexp3=exp3.typeCheck(typeEnv);

        if(typexp1.equals(new BoolType()) && typexp2.equals(typev) && typexp3.equals(typev)) return typeEnv;
        else throw new MyException("The conditional assignment is invalid");

    }

    @Override
    public String toString() {
        return v+"=("+exp1.toString()+")?"+exp2.toString()+":"+exp3.toString();
    }
}
