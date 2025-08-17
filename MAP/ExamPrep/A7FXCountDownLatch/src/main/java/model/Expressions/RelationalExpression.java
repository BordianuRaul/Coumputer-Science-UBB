package model.Expressions;

import model.Exceptions.MyException;
import model.Types.BoolType;
import model.Types.IntType;
import model.Types.InterfaceType;
import model.Values.BoolValue;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

public class RelationalExpression implements InterfaceExpression{

    private InterfaceExpression expression1, expression2;
    private String operation;

    public RelationalExpression(InterfaceExpression ex1, InterfaceExpression ex2, String op){
        this.expression1 = ex1;
        this.expression2 = ex2;
        this.operation = op;
    }

    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable, InterfaceMyDictionaryHeap<InterfaceValue> heap) throws MyException {
        InterfaceValue v1,v2;
        v1= expression1.evaluate(symbolTable,heap);
        if(v1.getType().equals(new IntType())){
            v2= expression2.evaluate(symbolTable,heap);
            if(v2.getType().equals(new IntType())){
                IntValue i1,i2;
                i1=(IntValue)v1;
                i2=(IntValue)v2;
                int n1,n2;
                n1=i1.getValue();
                n2= i2.getValue();
                if(operation.equals("<")) return new BoolValue(n1<n2);
                if(operation.equals("<=")) return new BoolValue(n1<=n2);
                if(operation.equals("==")) return new BoolValue(n1==n2);
                if(operation.equals("!=")) return new BoolValue(n1!=n2);
                if(operation.equals(">")) return new BoolValue(n1>n2);
                if(operation.equals(">=")) return new BoolValue(n1>=n2);
                throw new MyException("Invalid operand");
            }
            else throw new MyException("Second operand is not an integer");
        }
        else throw new MyException("First operand is not an integer");
    }

    @Override
    public InterfaceType typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType typ1, typ2;
        typ1= expression1.typeCheck(typeEnv);
        typ2= expression2.typeCheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            } else
                throw new MyException("second operand is not an integer");
        }else
            throw new MyException("first operand is not an integer");
    }
    @Override
    public String toString() {
        return expression1.toString() + operation + expression2.toString();
    }
}
