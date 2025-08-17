package Controller;

import Model.Item;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    IRepository repository;

    public Controller(IRepository repository){this.repository = repository;}

    public void add(Item item)
    {
        repository.add(item);
    }

    public Item[] getAll()
    {
        return repository.getAll();
    }

    public List<Item> filter(int minWeight)
    {
        List<Item> result = new ArrayList<>();
        for(Item item: repository.getAll())
        {
            if(item.getWeight() >= minWeight)
                result.add(item);
        }

        return result;
    }

}
