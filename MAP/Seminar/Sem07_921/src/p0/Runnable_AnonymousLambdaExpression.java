package p0;


class RunnableImpl implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello world! with "+ "anonymous class");
    }

}

public class Runnable_AnonymousLambdaExpression {

    public static void main(String[] a) {

        //class with name
        RunnableImpl r0= new RunnableImpl();

        //anonymous class
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world! with "+ "anonymous class");
            }
        };

        Runnable r11 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world! with "+ "anonymous class");
            }
        };

        // Lambda Runnable
        Runnable r2 = () -> {System.out.println("Hello world! with "+"lambda expression"); };

        //lambda expression as object
        Runnable r3 = r2;

        // Run them!
        r0.run();
        r1.run();
        r2.run();
        r3.run();
    }

}