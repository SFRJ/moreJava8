package part3;

import part3.other.ACustomImplementation;
import part3.other.AnInterfaceThatUsesDefaultKeyword;
import utils.Artist;

import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static utils.ArtistFixture.someArtistsWithInstruments;

public class UsefulFunctionalLibraries {
    /*
        One primitive int is 4 bytes while one Integer object is 14 bytes.
        Generic data structures in java cannot hold primitives, they need to use wrapper classes.
        Java 8 libraries provide special methods to work with streams that need to perform primitive data
    */

    public void mappingToPrimitiveInts(List<Artist> artists) {
        int totalNumberOfInstruments = artists.stream().mapToInt(artist -> artist.getInstruments().size()).sum();
        System.out.println(totalNumberOfInstruments);
    }

    /*
        Rules when overloading methods.
        1 - If there is a single possible target type, the lambda expression infers the type from
        the corresponding argument on the functional interface.
        2 - If there are several possible target types, the most specific type is inferred.
        3 - If there are several possible target types and there is no most specific type, you must manually provide a type.
    */

    public void callinOverloadedMethodsThatUseFunctionsAsParameters() {
        method((String value) -> false);
        method((int value) -> false);
    }

    public void method(Predicate<String> predicate) {

    }
    public void method(IntPredicate predicate) {

    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    //The annotation @FunctionalInterface is not mandatory but will tell the compiler
    //to strictly verify that this interface is in fact a functional interface.
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////DEFAULT METHODS ON INTERFACES////////////////////////////////////
    //Java 8 provides a new default keyword that allows interfaces to have default implementations
    //for their methods.
    // - A method marked as default doesnt have to be implemented if it has an implementaion in the interface does
    // not mandate to implement the interface if there is a default implementation
    /////////////////////////////////////////////////////////////////////////////////////////////




    //Returning a null value is seen in the industry as a very bad practice, even its creator "Tony Hoare" described it as
    // "my billion dollar mistake". Java 8 encourages to use Optional fields for those values that potentially could be null.
    public void usingOptional() {
        Optional<String> optionalValueA = of("SomeValue");// A concrete value given to the variable
        Optional<String> optionalValueB = empty();// An empty value given to the variable
        System.out.println(optionalValueB);//this will print Optional.empty
        System.out.println(optionalValueA.get());//we use get() to access the optional value
        //System.out.println(optionalValueB.get());//if we try to call get when the value is empty, we get a NoSuchElementException: No value present
        //We can test if the value is there by using the isPresent() method
        if(optionalValueB.isPresent())System.out.println(optionalValueB.get());
        else System.out.println("The value was not present");
        //We can use the orElse(), it will provide an alternative value when the Optional value is empty
        System.out.println(optionalValueB.orElse("OptionalValueB is not present"));
        //Also We can use the orElseGet(), which can give us the option to construct a new value via supplier when the OptionalValue is empty
        System.out.println(optionalValueB.orElseGet(() -> "Regenerated value:" + optionalValueA.get()));
    }

    //Using static methods in interface
    private interface DemoStaticInterfaceMethods{
        public static void someMethod() {
            System.out.println("OMG, I am writing implementation in an interface!");
        }
    }

    private class ImplementingAnInterfaceWithStaticMethod implements DemoStaticInterfaceMethods {
        /*
        Static methods from interfaces cannot be overridden.
        Often developers create util classes with static methods, now with this possibility on the interfaces
        that is no longer necessary.

        Important: Note that default methods are different, they can be overridden but is not mandated.
        */

    }

    public static void main(String[] args) {
        UsefulFunctionalLibraries libraries = new UsefulFunctionalLibraries();
        libraries.mappingToPrimitiveInts(someArtistsWithInstruments());
        AnInterfaceThatUsesDefaultKeyword someObject = new ACustomImplementation();
        someObject.method();
        libraries.usingOptional();
        //Calling static method from interface
        DemoStaticInterfaceMethods.someMethod();
    }

    /*
    * KEY POINTS
    * - Java 8 allows static interfaces. Now the only thing that make them different from an abstract class, is that they support multiple inheritance.
    * - Default methods on interface allow to provide a default implementation for the interface method. Remember that specific implementations of the interface
    * have priority.
    * - Optional is a neat alternative to using null
    * */
}
