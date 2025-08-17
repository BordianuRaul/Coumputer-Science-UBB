package Repository;

import Model.Animal;

public interface IRepository {

    public void add(Animal animal);
    public void delete(Animal animal);
    public Animal[] getAll();

    public int getSize();
}
