package part2;

import utils.Artist;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

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
}
