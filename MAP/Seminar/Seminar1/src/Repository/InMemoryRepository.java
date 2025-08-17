package Repository;

import Model.Item;

public class InMemoryRepository implements IRepository{

    private Item[] items;
    int size;

    public InMemoryRepository()
    {
        this.items = new Item[100];
        this.size = 0;
    }

    @Override
    public void add(Item item) {
        items[size++] = item;
    }

    @Override
    public void remove(Item item) {

    }

    @Override
    public Item[] getAll() {
        return items;
    }
}
