package model.Expressions;

import model.Exceptions.MyException;
import model.Types.IntType;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;

public class ArithmeticExpression implements InterfaceExpression {

    private InterfaceExpression expression1;
    private InterfaceExpression expression2;
    private int operationType; // 1-plus, 2-minus, 3-star, 4-divide


    public ArithmeticExpression(String operationType, InterfaceExpression expression1, InterfaceExpression expression2)
    {
        this.expression1 = expression1;
        this.expression2 = expression2;
        if(operationType == "+")
            this.operationType = 1;
        else if(operationType == "-")
             this.operationType = 2;
            else if(operationType == "*")
                this.operationType = 3;
                 else this.operationType = 4;

    }

    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable) throws MyException {

        InterfaceValue evaluation1 = expression1.evaluate(symbolTable);
        if (evaluation1.getType().equals(new IntType()))
        {
            InterfaceValue evaluation2 = expression2.evaluate(symbolTable);
            if (evaluation2.getType().equals(new IntType()))
            {
                IntValue operand1 = (IntValue) evaluation1;
                IntValue operand2 = (IntValue) evaluation2;
                int value1, value2;
                value1 = operand1.getValue();
                value2 = operand2.getValue();
                if (operationType == 1) return new IntValue(value1 + value2);
                if (operationType == 2) return new IntValue(value1 - value2);
                if (operationType == 3) return new IntValue(value1 * value2);
                if (operationType == 4)
                {
                    if (value2 == 0) throw new MyException("division by zero");
                    else return new IntValue(value1 / value2);
                }
            }
            else
            {
                throw new MyException("second operand is not an integer");
            }
        }
        else
        {
            throw new MyException("first operand is not an integer");
        }
        return evaluation1;
    }

    @Override
    public String toString() {
        String operator = "";
        if (operationType == 1) {
            operator = "+";
        } else if (operationType == 2) {
            operator = "-";
        } else if (operationType == 3) {
            operator = "*";
        } else if (operationType == 4) {
            operator = "/";
        }

        return "(" + expression1.toString() + " " + operator + " " + expression2.toString() + ")";
    }

}
