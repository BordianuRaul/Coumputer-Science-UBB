package model.Statements;

import model.Exceptions.MyException;
import model.Exceptions.OpenFileException;
import model.Expressions.InterfaceExpression;
import model.ProgramState;
import model.Types.StringType;
import model.Values.InterfaceValue;
import model.Values.StringValue;
import utils.InterfaceMyDictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFileStatement implements InterfaceStatement{

    InterfaceExpression expression;

    public OpenReadFileStatement(InterfaceExpression expression)
    {
        this.expression = expression;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException, IOException {

        StringValue filePath = (StringValue) this.expression.evaluate(state.getSymbolTable());
        if(!filePath.getType().equals(new StringType()))
            throw new OpenFileException("The file that was tried to be opened is not of a string.\n");

        InterfaceMyDictionary<InterfaceValue, BufferedReader> fileTable = state.getFileTable();

        if(fileTable.getKeys().contains(filePath))
            throw new OpenFileException("There already exists an open file with this path.\n");

        BufferedReader reader;

        try{

            reader = new BufferedReader(new FileReader(filePath.getValue()));
            fileTable.put(filePath, reader);
            state.setFileTable(fileTable);
        }
        catch(IOException ioException)
        {
            throw new IOException("File could not be opened");
        }
//        catch(MyException openFileException)
//        {
//            throw new OpenFileException("The file does not exist.\n");
//        }

        return state;
    }
}
