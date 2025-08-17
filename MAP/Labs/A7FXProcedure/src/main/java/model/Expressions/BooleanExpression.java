package model.Expressions;

import model.Exceptions.ComparisonException;
import model.Exceptions.MyException;
import model.Types.BoolType;
import model.Types.IntType;
import model.Types.InterfaceType;
import model.Values.BoolValue;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

public class BooleanExpression implements InterfaceExpression {
    private InterfaceExpression expressionLeftHandSide;
    private InterfaceExpression expressionRightHandSide;
    private String comparisonOperator;

    public BooleanExpression(InterfaceExpression expressionLeftHandSide, InterfaceExpression expressionRightHandSide, String comparisonOperator){
        this.comparisonOperator = comparisonOperator;
        this.expressionLeftHandSide = expressionLeftHandSide;
        this.expressionRightHandSide = expressionRightHandSide;
    }

    public InterfaceExpression getExpressionLeftHandSide() {
        return expressionLeftHandSide;
    }

    public InterfaceExpression getExpressionRightHandSide() {
        return expressionRightHandSide;
    }

    public String getComparisonOperator() {
        return comparisonOperator;
    }

    public void setComparisonOperator(String comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public void setExpressionLeftHandSide(InterfaceExpression expressionLeftHandSide) {
        this.expressionLeftHandSide = expressionLeftHandSide;
    }

    public void setInterfaceExpression_right(InterfaceExpression expressionRightHandSide) {
        this.expressionRightHandSide = expressionRightHandSide;
    }

    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable, InterfaceMyDictionaryHeap<InterfaceValue> heap) throws MyException {
        IntValue expr1 = (IntValue) expressionLeftHandSide.evaluate(symbolTable, heap);
        IntValue expr2 = (IntValue) expressionRightHandSide.evaluate(symbolTable, heap);
        boolean booleanEvaluationResult = switch (comparisonOperator) {
            case "<" -> expr1.getValue() < expr2.getValue();
            case "<=" -> expr1.getValue() <= expr2.getValue();
            case ">" -> expr1.getValue() > expr2.getValue();
            case ">=" -> expr1.getValue() >= expr2.getValue();
            case "==" -> expr1.equals(expr2);
            case "!=" -> !expr1.equals(expr2);

            default -> throw new ComparisonException(comparisonOperator + " is not a valid comparison operator.");
        };

        return booleanEvaluationResult ? new BoolValue(true) : new BoolValue(false);
    }

    @Override
    public String toString() {
        return "( " + expressionLeftHandSide.toString() + " " + comparisonOperator + " " + expressionRightHandSide.toString() + " )";
    }

    @Override
    public InterfaceType typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType type1, type2;
        type1 = expressionLeftHandSide.typeCheck(typeEnv);
        type2 = expressionRightHandSide.typeCheck(typeEnv);
        if(type1.equals(new IntType())){
            if(type2.equals(new IntType())){
                return new BoolType();
            }
            else throw new MyException("Second operand is not an integer!");
        }
        else throw new MyException("First operand is not an integer!");
    }

}



