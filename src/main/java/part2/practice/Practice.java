package part2.practice;

import utils.Album;
import utils.Artist;
import utils.Instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Practice {

    //A function that adds up numbers
    public void addNumbers(List<Integer> numbers) {
        System.out.println(numbers.stream().reduce((integer, integer2) -> integer + integer2).get());
    }

    //A function that takes a artists and return a list of artists names and places of origin
    public List<String> artistsNamesAndPlacesOfOrigin(List<Artist> artists) {
        return artists.stream().map((artist) -> artist.getName() + " is from " + artist.getOrigin()).collect(toList());
    }

    //A function that takes in artists and returns a list of artists with at most three instruments
    public List<Artist> albumsWithNoMoreThan3Songs(List<Artist> artists) {
        return artists.stream().filter((artist) -> artist.getInstruments().size() <=3).collect(toList());
    }

    //Convert this code sample from using external iteration to internal iteration
    public void internalIteration(List<Artist> artists) {
        int totalMembers = 0;
        for (Artist artist : artists) {
            Stream<Artist> members = artist.getMembers();
            totalMembers += members.count();
        }
    }

    public static void main(String[] args) {
        Practice practice = new Practice();
        practice.addNumbers(new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }});

        System.out.println(practice.artistsNamesAndPlacesOfOrigin(new ArrayList<Artist>(){{
            add(new Artist("Peter","England"));
            add(new Artist("Mark","Wales"));
        }})
        );
    }


}
