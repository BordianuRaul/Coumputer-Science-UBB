package Controller;

import Model.Animal;
import Model.Fish;
import Model.Tortoise;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    IRepository repository;

    public Controller(IRepository repository)
    {
        this.repository = repository;
    }
    public void add(String name, int age)
    {
        Fish toAddFish = new Fish(name, age);
        repository.add(toAddFish);
    }

    public void add(String name, int age, int size)
    {
        Tortoise toAddTortoise = new Tortoise(name, age, size);
        repository.add(toAddTortoise);
    }

    public void delete(String name, int age)
    {
        Fish toDeleteFish = new Fish(name, age);
        repository.delete(toDeleteFish);
    }

    public void delete(String name, int age, int size)
    {
        Tortoise toDeleteTortoise = new Tortoise(name, age, size);
        repository.delete(toDeleteTortoise);
    }

    public List<Animal> filterByAge(int age)
    {
        List<Animal>result = new ArrayList<>();
        Animal[] animals = repository.getAll();;
        int size = repository.getSize();

        for(int i = 0; i < size; i++)
        {
            if(animals[i].getAge() >= age)
                result.add(animals[i]);
        }
        return result;
    }

    public Animal[] getAll()
    {
        return repository.getAll();
    }
    public int getSize() {return repository.getSize();}
}
