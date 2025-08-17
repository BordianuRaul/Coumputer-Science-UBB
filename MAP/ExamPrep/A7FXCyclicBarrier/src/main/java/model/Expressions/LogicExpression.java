package model.Expressions;

import model.Exceptions.MyException;
import model.Types.BoolType;
import model.Types.InterfaceType;
import model.Values.BoolValue;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

public class LogicExpression implements InterfaceExpression{
    private InterfaceExpression expression1;
    private InterfaceExpression expression2;
    private String operationType;

    public LogicExpression(InterfaceExpression expression1, InterfaceExpression expression2, String operationType){
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operationType = operationType;
    }



    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable, InterfaceMyDictionaryHeap<InterfaceValue> heap) throws MyException {
        InterfaceValue v1,v2;
        v1 = expression1.evaluate(symbolTable,heap);

        if(v1.getType().equals(new BoolType())) {
            v2 = expression2.evaluate(symbolTable,heap);
            if(v2.getType().equals(new BoolType())){
                BoolValue i1=(BoolValue)v1;
                BoolValue i2=(BoolValue)v2;

                boolean b1,b2;
                b1=i1.getValue();
                b2=i2.getValue();
                if(operationType=="and")return new BoolValue(b1&&b2);
                if(operationType=="or") return new BoolValue(b1||b2);
                throw new MyException("Undefined operation");
            }
            throw new MyException("Second operand is not a bool");
        }
        throw new MyException("First operand is not a bool");
    }

    @Override
    public String toString()
    {
        return expression1.toString() + operationType + expression2.toString();
    }

    @Override
    public InterfaceType typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);

        if (type1.equals(new BoolType())) {
            if (type2.equals(new BoolType())) {
                return new BoolType();
            } else {
                throw new MyException("second operand is not a boolean");
            }
        } else {
            throw new MyException("first operand is not a boolean");
        }
    }
}
