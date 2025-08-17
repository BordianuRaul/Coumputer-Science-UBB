package Model;

public class Tortoise implements Animal{

    int age;
    String name;
    int size;

    public Tortoise(String name, int age, int size){
        this.age = age;
        this.name = name;
        this.size = size;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString()
    {
        return "Name:" + name + "\n" + "Age: " + Integer.toString(age) + "\n" + "Size: " + Integer.toString(size) + "\n";
    }
}
