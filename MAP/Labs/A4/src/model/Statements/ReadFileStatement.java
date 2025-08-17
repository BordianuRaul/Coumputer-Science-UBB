package model.Statements;

import model.Exceptions.FileException;
import model.Exceptions.MyException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.IntType;
import model.Values.IntValue;
import model.Values.InterfaceValue;
import model.Values.StringValue;
import utils.InterfaceMyDictionary;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements InterfaceStatement{

    InterfaceExpression expression;
    String variableName;

    public ReadFileStatement(InterfaceExpression expression, String variableName)
    {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {

        InterfaceMyDictionary<String, InterfaceValue> symbolTable = state.getSymbolTable();

        if(!symbolTable.isDefined(variableName))
            throw new FileException("The variable is not defined in symbol table!\n");
        if(!symbolTable.lookup(variableName).getType().equals(new IntType()))
            throw new FileException("The variable type is not int.");
        StringValue filePath;
        try
        {
            filePath = (StringValue) expression.evaluate(state.getSymbolTable(), state.getHeap());
        }
        catch (MyException myException)
        {
            throw new MyException("The expression value is not a string!\n");
        }

        if(!state.getFileTable().isDefined(filePath))
            throw new FileException("The file is not opened!\n");

        BufferedReader reader = state.getFileTable().lookup(filePath);
        String line;
        try{
            line = reader.readLine();
        } catch (IOException e) {
            throw new FileException("Cannot read from the file!\n");
        }

        IntValue value;
        if(line == null){
            value = new IntValue(0);
        }
        else
            try {
                value = new IntValue(Integer.parseInt(line));
            }
            catch (NumberFormatException e){
                value = new IntValue(0);;
            }
        symbolTable.put(variableName, value);

        return state;
    }
}
