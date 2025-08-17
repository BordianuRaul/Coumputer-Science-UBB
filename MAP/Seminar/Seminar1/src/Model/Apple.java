package Model;

public class Apple implements Item{

    private int weight;

    public Apple() {this(0);}

    public Apple(int weight){this.weight = weight;}

    @Override
    public int getWeight(){return weight;}

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                '}';
    }
}
