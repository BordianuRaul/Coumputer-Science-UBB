package model.Expressions;

import model.Exceptions.ComparisonException;
import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.Values.BoolValue;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import view.Interpreter;

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
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable) throws MyException {
        IntValue expr1 = (IntValue) expressionLeftHandSide.evaluate(symbolTable);
        IntValue expr2 = (IntValue) expressionRightHandSide.evaluate(symbolTable);
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


}
