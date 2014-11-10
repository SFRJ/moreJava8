package part3;

import utils.Artist;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

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

    public static void main(String[] args) {
        UsefulFunctionalLibraries libraries = new UsefulFunctionalLibraries();
        libraries.mappingToPrimitiveInts(someArtistsWithInstruments());
    }
}
