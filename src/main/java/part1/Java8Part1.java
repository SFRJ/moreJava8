package part1;

import utils.Artist;
import utils.SomeFunctionalInterface;

import java.util.function.*;

public class Java8Part1 {

    //EXAMPLE 1
    public void simpleLambdaExample(){
       //Before Lambda
       Runnable taskA = new Runnable() {
            @Override
            public void run() {
                System.out.println("Something");
            }
        };

        taskA.run();

        //With Lambda expressions you have to be assigned to an functional interface(An interface with just one function) ALWAYS!
        Runnable taskB = () -> {System.out.println("Hello");};

        taskB.run();//One reason why we use lambdas when we want to execute something at a latter point in time
    }

    //EXAMPLE 2
    public void lambdaExampleWithParameters(){

        //Before Lambda
        SomeFunctionalInterface taskA = new SomeFunctionalInterface() {
            @Override
            public void uniqueMethod(String parameter) {
                System.out.println("Hi " + parameter);
            }
        };

        taskA.uniqueMethod("Djordje");

        //With Lambda expressions, you can also pass parameters to the implementation of the functional interfaces method
        SomeFunctionalInterface taskB = parameter -> System.out.println("Hi " + parameter);

        taskB.uniqueMethod("Djordje");
    }

    //EXAMPLE 3

    //The lambdas can be passed as parameters to methods, this allows to pass code as data.
    public void passingCodeAsData() {
        //The type(SomeFunctionalInterface) can be automatically inferred from the type of the argument
        someClientMethod((parameter) -> System.out.println("Hello " + parameter));
    }

    //A method that takes a functional interface and uses it
    public void someClientMethod(SomeFunctionalInterface anInterface) {
        anInterface.uniqueMethod("Djordje!");
    }

    //EXAMPLE 4
    public void valuesPassedToLambdasMustBeFinal() {
        String name = "Djordje!";
        //name = "something else"; COMPILATION ERROR!!!
        //The variables that are passed to Lambda expressions, can only be assigned once.(But not necessarily need marked as final).
        Runnable task = () -> System.out.println("Hello " + name);
        task.run();
    }

    //EXAMPLE 5
    public void importantFunctionalInterfacesInJava() {
        //Some important functional interfaces in Java and what their functional methods take and return

        //takes Object and returns boolean
        Predicate<String> predicate = (value) -> value.length() > 0;
        //takes Object and returns void
        Consumer<String> consumer = (value) -> System.out.println("Some " + value);
        //takes one type and returns another
        Function<Artist,String> function = (artist) ->  artist.getName();
        // doesn't take anything and returns an object(Mimics a factory method)
        Supplier<Artist> supplier = () -> new Artist();
        // takes a type and return the same type, it represents an operation on a single operand that produces a result of the same type as its operand.
        //Could be use as a decorator
        UnaryOperator<Artist> unaryOperator = (artist) -> artist;
        //Takes one type and return the same type. Represents an operation upon two operands of the same type, producing a result of the same type as the operands.
        BinaryOperator<Integer> binaryOperator = (a, b) -> a + b;
    }

    public static void main(String[] args) {
        Java8Part1 java8Part1 = new Java8Part1();
        java8Part1.simpleLambdaExample();
        java8Part1.lambdaExampleWithParameters();
        java8Part1.passingCodeAsData();
        java8Part1.valuesPassedToLambdasMustBeFinal();
    }

    /*
        SUMMARY
        - A lambda expression is a method without a name that is used to pass around behavior as if it were data.
        - A functional interface is an interface with a single abstract method that is used as the type of a lambda expression.
    */
}
