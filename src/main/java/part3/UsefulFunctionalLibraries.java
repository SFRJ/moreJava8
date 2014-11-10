package part3;

import utils.Artist;

import java.util.List;

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

    public static void main(String[] args) {
        UsefulFunctionalLibraries libraries = new UsefulFunctionalLibraries();
        libraries.mappingToPrimitiveInts(someArtistsWithInstruments());
    }
}
