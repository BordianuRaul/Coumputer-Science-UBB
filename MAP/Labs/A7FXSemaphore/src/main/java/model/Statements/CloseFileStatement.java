package model.Statements;

import model.Exceptions.MyException;
import model.Exceptions.OpenFileException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.InterfaceType;
import model.Types.StringType;
import model.Values.InterfaceValue;
import model.Values.StringValue;
import utils.InterfaceMyDictionary;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements InterfaceStatement{

    InterfaceExpression expression;

    public CloseFileStatement(InterfaceExpression expression) { this.expression = expression; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {

        StringValue filePath = (StringValue) this.expression.evaluate(state.getSymbolTable(), state.getHeap());
        if(!filePath.getType().equals(new StringType()))
            throw new OpenFileException("The file that was tried to be opened is not of a string.\n");

        InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable = state.getFileTable();
        if(!fileTable.getKeys().contains(filePath))
            throw new OpenFileException("There does not exist an open file with this path.\n");

        BufferedReader reader = state.getFileTable().lookup(filePath);
        reader.close();

        fileTable.delete(filePath);

        return null;
    }

    @Override
    public InterfaceMyDictionary<String, InterfaceType> typeCheck(InterfaceMyDictionary<String, InterfaceType> typeEnv) throws MyException {
        InterfaceType typeExpression = expression.typeCheck(typeEnv);
        if(typeExpression.equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("CloseReadFileStatement: expression is not a string");
    }

    @Override
    public String toString() {
        return "closeFile(" + expression.toString()+ ")";
    }
}
