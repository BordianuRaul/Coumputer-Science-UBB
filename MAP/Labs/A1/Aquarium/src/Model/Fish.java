package Model;

public class Fish implements Animal{

    String name;
    int age;

    public Fish(String name, int age)
    {
        this.name = name;
        this.age = age;
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
        return "Name:" + name + "\n" + "Age: " + Integer.toString(age) + "\n";
    }
}
