package controller;

import model.Exceptions.MyException;
import model.Statements.InterfaceStatement;
import model.ProgramState;
import model.Values.InterfaceValue;
import model.Values.ReferenceValue;
import repository.InterfaceRepository;
import utils.InterfaceMyDictionaryHeap;
import utils.InterfaceMyStack;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;


public class Controller {
    InterfaceRepository repo;

    public InterfaceRepository getRepo() {
        return repo;
    }

    public void setRepo(InterfaceRepository repo) {
        this.repo = repo;
    }

    public Controller(InterfaceRepository repository) {
        repo = repository;
    }

    List<Integer> getSafeAllAddresses(Collection<InterfaceValue> symbolTableValues, InterfaceMyDictionaryHeap<InterfaceValue> heapTable){

        ConcurrentLinkedDeque<Integer> symbolTableAddresses = symbolTableValues.stream()
                .filter(v-> v instanceof ReferenceValue)
                .map(v-> {ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toCollection(ConcurrentLinkedDeque::new));

        symbolTableAddresses.stream()
                .forEach(address-> {

                                InterfaceValue referenceValue =  heapTable.lookUp(address);
                                if (referenceValue instanceof  ReferenceValue)
                                    if (!symbolTableAddresses.contains( ((ReferenceValue) referenceValue).getAddress()))
                                        symbolTableAddresses.add(((ReferenceValue) referenceValue).getAddress());
                             });

        return symbolTableAddresses.stream().toList();
    }


    Map<Integer, InterfaceValue> safeGarbageCollector(List<Integer> symbolTableAddresses, Map<Integer,InterfaceValue> heap){
        return heap.entrySet().stream()
                .filter(element->symbolTableAddresses.contains(element.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public ProgramState oneStep(ProgramState state) throws MyException, IOException {

        InterfaceMyStack<InterfaceStatement> stack= state.getExecutionStack();
        if(stack.isEmpty()) throw new MyException("Program state stack is empty\n.");
        InterfaceStatement currentStatement = stack.pop();
        return currentStatement.execute(state);

    }

    public void allStep() throws MyException {

        try {

            ProgramState currentProgramState = repo.getCurrentProgramState();
            repo.logProgramStateExecutable();
            while (!currentProgramState.getExecutionStack().isEmpty()) {

                oneStep(currentProgramState);
                repo.logProgramStateExecutable();

                currentProgramState.getHeap().setContent(
                        safeGarbageCollector(
                                getSafeAllAddresses(currentProgramState.getSymbolTable().getContent().values(), currentProgramState.getHeap()),currentProgramState.getHeap().getContent()));
                repo.logProgramStateExecutable();

            }
        }
        catch (IOException ioException)
        {
            System.out.println(ioException.getMessage());
        }
        catch (MyException myException)
        {
            System.out.println("An error has occurred!\n" + myException.getMessage());
        }
    }

}
