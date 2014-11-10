package utils;

import java.util.ArrayList;
import java.util.List;

import static utils.Instrument.instrument;

public class ArtistFixture {

    public static List<Artist> someArtistsWithInstruments() {

        Artist artistA = new Artist("Peter", "England");
        artistA.setInstruments(new ArrayList<Instrument>() {
            {
                add(instrument("guitar", "red"));
                add(instrument("bass", "blue"));
            }
        });
        Artist artistB = new Artist("Peter", "England");
        artistB.setInstruments(new ArrayList<Instrument>() {
            {
                add(instrument("trumpet", "pink"));
            }
        });

        return new ArrayList<Artist>() {
            {
                add(artistA);
                add(artistB);
            }
        };
    }


}
