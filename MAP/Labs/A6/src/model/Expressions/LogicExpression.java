package model.Expressions;

import model.Exceptions.MyException;
import model.Types.BoolType;
import model.Types.InterfaceType;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

public class LogicExpression implements InterfaceExpression{
    private InterfaceExpression expression1;
    private InterfaceExpression expression2;
    private int operationType;
    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable, InterfaceMyDictionaryHeap<InterfaceValue> heap) throws MyException {
        return null;
    }
    @Override
    public String toString()
    {
        return null;
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
