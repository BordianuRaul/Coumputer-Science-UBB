package model.Expressions;

import model.Exceptions.MyException;
import model.Types.InterfaceType;
import model.Types.ReferenceType;
import model.Values.InterfaceValue;
import model.Values.ReferenceValue;
import utils.InterfaceMyDictionary;
import utils.InterfaceMyDictionaryHeap;

public class HeapReadingExpression implements InterfaceExpression {

    InterfaceExpression expression;

    public HeapReadingExpression(InterfaceExpression expression){this.expression = expression;}
    @Override
    public InterfaceValue evaluate(InterfaceMyDictionary<String, InterfaceValue> symbolTable, InterfaceMyDictionaryHeap<InterfaceValue> heap) throws MyException {

        ReferenceValue value;

        try{
            value = (ReferenceValue) expression.evaluate(symbolTable, heap);
        }
        catch(ClassCastException exception){
            throw new MyException("The expression was not evaluated to a reference value.\n");
        }

        if(!heap.isDefined(value.getAddress()))
        {
            throw new MyException("The address resulted from the expression is not defined.\n");
        }

        return heap.getContent().get(value.getAddress());

    }

    @Override
    public String toString(){
        return "read from heap (" + expression.toString() + ")";
    }

    @Override
    public InterfaceType typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType type = expression.typeCheck(typeEnv);
        if (type instanceof ReferenceType){
            ReferenceType referenceType = (ReferenceType) type;
            return referenceType.getInner();
        }
        else{
            throw new MyException("Expression not of type ReferenceType");
        }
    }

}
