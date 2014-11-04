package part2;

import utils.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class StreamsBasics {

    /*
    Old school loops have lots of boiler plate code that blurs their purpose make them difficult to read.
    This old school loops are either implemented with an iterator or with counters both of them are.
    */

    //[OLD SCHOOL]A for loop with both external and internal counters and lots of boilerplate.
    public void evilOldSchoolForLoop(Artist[] artists) {
        int numberOfUkArtists = 0;
        for (int index = 0; index < artists.length; index++) {
            if(artists[index].isFrom("UK")) {
                numberOfUkArtists++;
            }
        }
    }

    //[OLD SCHOOL]A while loop with that uses iterators and counters, this has lots of boilerplate also.
    public void evilOldSchoolWhileLoop(List<Artist> artists) {
        int numberOfUkArtists = 0;
        Iterator<Artist> artistIterator = artists.iterator();
        while (artistIterator.hasNext()) {
            Artist artist = artistIterator.next();
            if(artist.isFrom("UK")) {
                numberOfUkArtists++;
            }
        }
    }
    //////////////////////////////////////////////////////////////////////////////
    //[WITH JAVA 8]Using streams and lambdas to write code with less boilerplate//
    //////////////////////////////////////////////////////////////////////////////
    public void countingElementsWithJava8(List<Artist> artists) {
        long numberOfUkArtists = artists.stream().filter((artist) -> artist.isFrom("UK")).count();
    //Note: Remember that, (artist) is argument that will be reaching the method defined in the functional interface, the compiler will infer the type.
    }

    public void streamObjectsAreEvaluatedLazily(List<Artist> artists) {
        //This code will not print anything because the stream is not terminated(There is no termination method),
        //so it results in lazy evaluation of the objects that travel through it.
        artists.stream()
            .filter((artist) -> {
                System.out.println(artist.getName());
                return artist.isFrom("UK");
            });

        //To make sure the print occurs, we just need to include some sort of termination statement(e.g count())
        artists.stream()
                .filter((artist) -> {
                    System.out.println(artist.getName());
                    return artist.isFrom("UK");
                }).count();

        //There are multiple termination methods to use depending on what we want to do with the stream.
        // A very useful one that we can use when we want to perform an action without actually consume the stream, is peek()
        artists.stream().peek(artist -> {System.out.println("Something");});

    }

    //To get the objects from an stream as a list there is a termination method called collect, to which you need to pass a collector.
    public void collectingResults(List<Artist> artists) {
        artists.stream().filter((artist) -> artist.isFrom("England")).collect(toList());
    }


    //Map function is used when you need to transform an object type into a completely different thing
    //Map takes as parameter a Function, which is basically something that takes an input and returns an output
    public void mappingStreams(List<Artist> artists) {
        //example 1
        artists.stream().map((artist) -> new SuperHero(artist.getName())).collect(toList());
        //example 2
        asList("a","b","c").stream().map((element) -> element.toUpperCase());
    }

    //Filter is used to select only those elements in the stream that you are interested in
    //Filter takes as parameter a Predicate, which actually is something that returns a boolean value
    public void filteringStreams(List<Artist> artists) {
        artists.stream().filter((artist) -> artist.isFrom("England")).collect(toList());
    }

    //Returns a stream consisting of the results of replacing each element of
    // this stream with the contents of a mapped stream produced by applying
    // the provided mapping function to each element.
    public void flatmappingStreams(List<Artist> europeamArtist) {
        //This example creates a new stream of all the instruments that all the artists have
        Stream<Instrument> instrumentStream = europeamArtist.stream().flatMap(artist -> artist.getInstruments().stream());
        //IMPORTANT NOTE 1: This flat map will contain duplications
        List<Instrument> everyInstrumentFromAllArtists = instrumentStream.collect(toList());

        //IMPORTANT NOTE 2: If we wanted to avoid having 2 equal instruments(Lets say of the same type, or colour), we would need
        //to use a set collector, but also we would have to provide in the Instrument object, an implementation of equals() and hashcode().
        Set<Instrument> everyInstrumentFromAllArtistsWithNoDuplication = instrumentStream.collect(toSet());

        //Aside note, to the result once we gather all instruments, we can use filters if we wanted to filter just wind instruments.
    }

    //Min and Max values are commonly used to find specific elements in streams.
    public void minAndMaxValues(List<Artist> artists) {
        Artist instrumentWithTheLongestName = artists.stream().max(comparing((artist) -> artist.getName().length())).get();
        Artist instrumentWithTheSortestName = artists.stream().min(comparing((artist) -> artist.getName().length())).get();
    }

    /*
        Reduce is basically taking an specific value each of the elements of an stream, and giving it to an accumulator object.
        See below how the gangster gets protection money from the people.
    */

    public void reduceToASingleElement(List<Person> persons) {
        Person ganster = new Person();
        persons.stream().reduce(ganster, (gangster, person) -> {
            gangster.setMoneyInPocket(gangster.getMoneyInPocket() + person.getMoneyInPocket());
            return gangster;
        });
    }

    /*
        We can iterate each of the elements of an stream using forEach()
    */
   public List<String> iteratingEachOfElementNamesWithForEach(List<Album> albums) {
       //Method references can be used when the lambda just needs to reference a method.
       //This: album -> album.getTracks() is equal to Album::getTracks
       List<String> output = new ArrayList<>();
       albums.stream().forEach(album -> album.getSongs().
               filter(song -> song.lengthInSeconds() > 60).
               map(Song::name).forEach(output::add));
       return output;
   }

    /*
        The previous example can be translated to a more idiomatic style using a flat map
    */
    public Set<String> iteratingEachOfElementNamesWithFlatMap(List<Album> albums) {
        return albums.stream()
                .flatMap(Album::getSongs)
                .filter(song -> song.lengthInSeconds() > 60)
                .map(Song::getName)
                .collect(toSet());
        //Note: To avoid misusing the stream, it is recommended not to assign to local variables intermediate results, open the least streams as possible
    }

    /*
        We call higher order functions to the functions that either take a function as an argument or return one(Functional interfaces).
        Higher order functions will allow to focus on the "what" and ont the "how"
    */
    public Predicate<String> returningAHigherOrderFunction() {
        return value -> value.length() > 10;
    }

    public void takingAHigherOrderFunctionAsParameter(Predicate<String> validator) {
        List<String> words = new ArrayList<>();
        words.add("plane");
        words.add("apple");
        words.stream().filter(validator);
    }

}
