package Repository;

import Model.Animal;

import java.util.Objects;

public class InMemoryRepository implements IRepository{

    private Animal[] data;
    private int size;

    public InMemoryRepository() {
        this.data = new Animal[100];
        this.size = 0;
    }
    @Override
    public int getSize()
    {
        return this.size;
    }

    @Override
    public void add(Animal animal) {
        data[size] = animal;
        size++;
    }

    @Override
    public void delete(Animal animal) {

        for(int i = 0; i < size; i++)
        {
            if(Objects.equals(data[i].getName(), animal.getName()) && data[i].getAge() == animal.getAge())
            {
                data[i] = data[size-1];
                size--;
            }
        }

    }

    @Override
    public Animal[] getAll() {
        return data;
    }

}
