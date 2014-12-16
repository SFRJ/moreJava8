package part4;

import utils.Album;
import utils.Artist;
import utils.Instrument;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class CollectionsAdvancedTopics {

    private CollectionsAdvancedTopics() {
    }

    public static CollectionsAdvancedTopics advancedTopics() {
        return new CollectionsAdvancedTopics();
    }

    public void callingMethodsWithLambdas(List<Artist> artists) {
        //This is how often you would call a getter method with a lambda
        artists.stream().map((artist) -> artist.getInstruments());
        //But if it is just a getter that you want to call, you can use the a shortcut
        artists.stream().map(Artist::getInstruments);
    }

    public void sorting() {
        //Sets don't guarantee that the order remains.
        //The order you add elements in may not be the same as the order in which you get the elements out
        Set<Integer> numbers = new HashSet<>(asList(4,3,2,1));
        System.out.println(numbers);
        //To guarantee that that the order remains when using a set, the elements need to explicitly be sorted
        List<Integer> sortedList = numbers.stream().sorted().collect(toList());
        System.out.println(sortedList);

        //List guarantees that the order remains.
        //The order you add elements in is the order in which you get the elements out, even when operations are performed in
        //each of the elements
        List<Integer> someOrders = new ArrayList<>(asList(1,2,3,4));
        System.out.println(someOrders.stream().map((x) -> x + 9).collect(toList()));

        //NOTE: reduce(), filter(), map()... and other functions work more efficiently on ordered streams.
        //WARN: forEach() function if they doesn't have ordered inputs, the output can be unpredictable.
    }

    public void differentTypesOfOutOfTheBoxCollectorsForDifferentPurposes(List<Artist> artists, List<Album> albums) {
        //Using collectors that use comparators
        Function<Artist,Long> numberOfMembers = artist -> artist.getMembers().count();
        artists.stream().collect(Collectors.maxBy(Comparator.comparing(numberOfMembers)));

        //Finding an average with collectors(Average number of instruments per artist)
        artists.stream().collect(Collectors.averagingInt(artist -> artist.getInstruments().size()));

        //Partitioning data based on true and false(putting in separate lists those artists who often play solo)
        Map<Boolean,List<Artist>> bands = artists.stream().collect(Collectors.partitioningBy(Artist::isSolo));

        //Grouping data based on an object of their internal state(e.g Groups of Albums by Main Artist)
        Map<Artist, List<Album>> albumsGroupedByMainArtist =
            albums.stream().collect(groupingBy(Album::getMainArtist));

        //Grouping advanced - finding metadata about the groups(e.g How many albums each Artist has)
        Map<Artist, Long> numberOfAlbumsEachArtist =
                albums.stream().collect(groupingBy(Album::getMainArtist,Collectors.counting()));

        //Grouping advanced - finding metadata about the groups(e.g Name of albums that each artist has produced)
        Map<Artist, List<String>> namesOfAlbumsEachArtistHasProduced =
                albums.stream().collect(groupingBy(Album::getMainArtist, Collectors.mapping(Album::getName,toList())));

        //Building Strings using collectors
        String result = artists.stream().map(Artist::getName).collect(Collectors.joining(",", "[", "]"));
    }

    public static void main(String[] args) {
        CollectionsAdvancedTopics advancedTopics = advancedTopics();
        advancedTopics.sorting();
    }
}
