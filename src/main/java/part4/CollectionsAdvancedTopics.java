package part4;

import utils.Artist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
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
    }

    public static void main(String[] args) {
        CollectionsAdvancedTopics advancedTopics = advancedTopics();
        advancedTopics.sorting();
    }
}
