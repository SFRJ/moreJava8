package part2.practice;

import javafx.util.Pair;
import utils.Album;
import utils.Artist;
import utils.Instrument;
import utils.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.valueOf;
import static java.util.Arrays.asList;
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


    //Count the number of lowercase letters in a String
    public long numberOfLowerCaseLetters(String input) {
        return input.chars().filter(value -> {
            char asCharacter = (char) value;
            return isUpperCase(asCharacter);
        }).count();
    }

    //Find the string with the larger number of lower case letters
    public String stringWithLargerNumberOfLowerCaseLetters(List <String> input) {
        return input.stream()
                .map(value -> new Word(value, (int) countLowerCaseLetters(value)))
                .reduce((word, word2) -> word.getLength() > word2.getLength() ? word : word2)
                .get().getContent();
    }

    private long countLowerCaseLetters(String current) {
        return current.chars().filter(value -> {
            char asCharacter = (char) value;
            return isLowerCase(asCharacter);
        }).count();
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

        System.out.println(practice.numberOfLowerCaseLetters("ABCDeFgH"));
        System.out.println(practice.stringWithLargerNumberOfLowerCaseLetters(
                new ArrayList<String>(){{
                add("DDDDDDDDD");
                add("aabC");
                add("AbC");
                add("AbCbbbbb");
                }}));
    }

}
