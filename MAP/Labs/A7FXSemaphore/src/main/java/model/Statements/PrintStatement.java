package model.Statements;

import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.InterfaceType;
import model.Values.InterfaceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;
import utils.InterfaceMyList;

public class PrintStatement implements InterfaceStatement {

    InterfaceExpression expression;

    public PrintStatement(InterfaceExpression expression) { this.expression = expression;}

    public String toString(){

        return "print( " + expression.toString() + " )";

    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {

        InterfaceMyList<InterfaceValue> out = state.getOut();
        InterfaceMyDictionary<String,InterfaceValue> table=state.getSymbolTable();
        InterfaceMyDictionaryHeap<InterfaceValue> heap=state.getHeap();

        try{
            out.add(expression.evaluate(table,heap));
        }catch(MyException e){
            throw new MyException(e.getMessage());
        }
        return null;

    }
}
