package part4;

import utils.Artist;

import java.util.List;

public class CollectionsAdvancedTopics {

    public void callingMethodsWithLambdas(List<Artist> artists) {
        //This is how often you would call a getter method with a lambda
        artists.stream().map((artist) -> artist.getInstruments());
        //But if it is just a getter that you want to call, you can use the a shortcut
        artists.stream().map(Artist::getInstruments);
        //

    }
}
