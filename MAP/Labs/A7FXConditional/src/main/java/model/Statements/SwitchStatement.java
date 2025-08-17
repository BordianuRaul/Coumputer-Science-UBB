package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.Expressions.RelationalExpression;
import model.ProgramState;
import model.Types.InterfaceType;
import utils.InterfaceMyDictionary;

public class SwitchStatement implements InterfaceStatement{

    private InterfaceExpression exp;
    private InterfaceExpression exp1;
    private InterfaceExpression exp2;
    private InterfaceStatement stmt1;
    private InterfaceStatement stmt2;
    private InterfaceStatement stmt3;

    public SwitchStatement(InterfaceExpression e,InterfaceExpression e1,InterfaceExpression e2,InterfaceStatement s1,InterfaceStatement s2,InterfaceStatement s3){
        exp=e;exp1=e1;exp2=e2;stmt1=s1;stmt2=s2;stmt3=s3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        InterfaceStatement newSwitch=new IfStatement(new RelationalExpression(exp,exp1,"=="),stmt1,
                new IfStatement(new RelationalExpression(exp,exp2,"=="),stmt2,stmt3));
        state.getExecutionStack().push(newSwitch);

        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType typexp=exp.typeCheck(typeEnv);
        InterfaceType typexp1=exp1.typeCheck(typeEnv);
        InterfaceType typexp2=exp2.typeCheck(typeEnv);

        if(typexp.equals(typexp1) && typexp.equals(typexp2)){
            stmt1.typeCheck(typeEnv.cloneDictionary());
            stmt2.typeCheck(typeEnv.cloneDictionary());
            stmt3.typeCheck(typeEnv.cloneDictionary());
            return typeEnv;
        }
        else
            throw new MyException("The expression types don't match");
    }

    @Override
    public String toString() {
        return "switch("+exp.toString()+") (case "+exp1.toString()+": "+stmt1.toString()+")(case "+exp2.toString()+": "+stmt2.toString()+")(default: "+stmt3.toString()+")";
    }
}
