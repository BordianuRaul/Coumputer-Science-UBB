package repository;

public interface IRepository implements IRepository{

    private List<PrgState> programs;

    void add(PrgState program)
    {
        programs.add(program);
    }

}
