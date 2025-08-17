package controller;

import model.Exceptions.MyException;
import model.Statements.InterfaceStatement;
import model.ProgramState;
import model.Values.InterfaceValue;
import model.Values.ReferenceValue;
import repository.FileRepository;
import repository.InterfaceRepository;
import utils.InterfaceMyDictionaryHeap;
import utils.InterfaceMyStack;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;


public class Controller {
    InterfaceRepository repo;

    ExecutorService executor;

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

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> currentProgramList){
        return currentProgramList.stream().filter(programState -> programState.isNotCompleted()).collect(Collectors.toList());
    }

    private void oneStepForAllPrograms(List<ProgramState> programStatesList) throws InterruptedException, MyException, IOException {

        programStatesList.forEach(program -> {
            try {
                repo.logProgramStateExecutable(program);
            } catch (MyException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        List<Callable<ProgramState>> callList = programStatesList.stream()
                .map((ProgramState currentProgramState) -> (Callable<ProgramState>)(() -> currentProgramState.oneStep()))
                .collect(Collectors.toList());

        List<ProgramState>newProgramStates = executor.invokeAll(callList).stream().
                map(future -> {try{return future.get();}
                catch(InterruptedException | ExecutionException exception){
                    throw new RuntimeException(exception);}
                }).filter(p->p!=null).collect(Collectors.toList());

        newProgramStates.addAll(programStatesList);

        programStatesList.forEach(program -> {
            try {
                repo.logProgramStateExecutable(program);
            } catch (MyException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        repo.setProgramList(newProgramStates);
    }

    public void allSteps() throws Exception {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> states = removeCompletedPrograms(repo.getProgramList());
        while(states.size() > 0){
            states.get(states.size() - 1).getHeap().setContent(
                    safeGarbageCollector(
                            getSafeAllAddresses(states.get(states.size() - 1).getSymbolTable().getContent().values(), states.get(states.size() - 1).getHeap()),states.get(states.size() - 1).getHeap().getContent()));
            oneStepForAllPrograms(states);
            states = removeCompletedPrograms(repo.getProgramList());
        }
        executor.shutdownNow();
        repo.setProgramList(states);

    }



}
