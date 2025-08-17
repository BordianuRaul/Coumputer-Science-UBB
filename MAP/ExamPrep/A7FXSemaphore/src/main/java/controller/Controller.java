package controller;


import model.Exceptions.MyException;
import model.ProgramState;
import model.Values.InterfaceValue;
import model.Values.ReferenceValue;
import repository.InterfaceRepository;
import utils.InterfaceMyDictionaryHeap;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Pair {
    final ProgramState first;
    final MyException second;

    public Pair(ProgramState first, MyException second) {
        this.first = first;
        this.second = second;
    }
}
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



    public Map<Integer, InterfaceValue> safeGarbageCollector(List<Integer> symTableAddresses, List<Integer> heapAddresses, Map<Integer, InterfaceValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> ( symTableAddresses.contains(e.getKey()) || heapAddresses.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public List<ProgramState> removeCompletedPrograms(List<ProgramState> currentProgramList){
        return currentProgramList.stream().filter(programState -> programState.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepForAllPrograms(List<ProgramState> programStates) throws MyException, InterruptedException {
        programStates.forEach(programState -> {
            try {
                repo.logProgramStateExecutable(programState);
            } catch (IOException | MyException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        });
        List<Callable<ProgramState>> callList = programStates.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep))
                .collect(Collectors.toList());

        List<Pair> newProgramList;
        newProgramList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return new Pair(future.get(), null);
                    } catch (ExecutionException | InterruptedException e) {
                        if (e.getCause() instanceof MyException)
                            return new Pair(null, (MyException) e.getCause());
                        System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
                        return null;
                    }
                }).filter(Objects::nonNull)
                .filter(pair -> pair.first != null || pair.second != null)
                .collect(Collectors.toList());

        for (Pair error: newProgramList)
            if (error.second != null)
                throw error.second;
        programStates.addAll(newProgramList.stream().map(pair -> pair.first).collect(Collectors.toList()));

        programStates.forEach(programState -> {
            try {
                repo.logProgramStateExecutable(programState);
            } catch (IOException | MyException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        });
        repo.setProgramList(programStates);
    }

//    public void allSteps() throws Exception {
//        executor = Executors.newFixedThreadPool(2);
//        List<ProgramState> states = removeCompletedPrograms(repo.getProgramList());
//        while(states.size() > 0){
//            states.get(states.size() - 1).getHeap().setContent(
//                    safeGarbageCollector(
//                            getSafeAllAddresses(states.get(states.size() - 1).getSymbolTable().getContent().values(), states.get(states.size() - 1).getHeap()),states.get(states.size() - 1).getHeap().getContent()));
//            oneStepForAllPrograms(states);
//            states = removeCompletedPrograms(repo.getProgramList());
//        }
//        executor.shutdownNow();
//        repo.setProgramList(states);
//
//    }

    public void executeOneStep()
    {
        executor = Executors.newFixedThreadPool(8);
        removeCompletedPrograms(repo.getProgramList());
        List<ProgramState> programStates = repo.getProgramList();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAllPrograms(repo.getProgramList());
            } catch (InterruptedException e) {
                System.out.println();
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
            programStates.forEach(e -> {
                try {
                    repo.logProgramStateExecutable(e);
                } catch (IOException e1) {
                    System.out.println();
                } catch (MyException ex) {
                    throw new RuntimeException(ex);
                }
            });
            removeCompletedPrograms(repo.getProgramList());
            executor.shutdownNow();
        }
    }

    public void oneStep() throws MyException, InterruptedException {
        executor= Executors.newFixedThreadPool(8);
        List<ProgramState> programStates = removeCompletedPrograms(repo.getProgramList());
        oneStepForAllPrograms(programStates);
        conservativeGarbageCollector(programStates);
        executor.shutdownNow();
    }

    public void conservativeGarbageCollector(List<ProgramState> programStates) {
        List<Integer> symTableAddresses = Objects.requireNonNull(programStates.stream()
                        .map(p -> getAddressesFromSymTable(p.getSymbolTable().getContent().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null))
                .collect(Collectors.toList());
        programStates.forEach(p -> p.getHeap().setContent((HashMap<Integer, InterfaceValue>) safeGarbageCollector(symTableAddresses, getAddressesFromHeap(p.getHeap().getContent().values()), p.getHeap().getContent())));
    }

    public List<Integer> getAddressesFromSymTable(Collection<InterfaceValue> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> {ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public List<Integer> getAddressesFromHeap(Collection<InterfaceValue> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> {ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public String toString(){
        return this.repo.toString();
    }

    public List<ProgramState> getProgramStates() {
        return this.repo.getProgramList();
    }

    public void setProgramStates(List<ProgramState> programStates) {
        this.repo.setProgramList(programStates);
    }



}
