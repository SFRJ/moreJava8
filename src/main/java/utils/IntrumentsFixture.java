package utils;

import java.util.ArrayList;
import java.util.List;

import static utils.Instrument.instrument;

public class IntrumentsFixture {

    public static List<Instrument> twoInstruments() {
        return new ArrayList<Instrument>() {
            {
                add(instrument("guitar", "brown"));
                add(instrument("bass", "red"));
            }
        };
    }

    public static List<Instrument> fiveInstruments() {
        return new ArrayList<Instrument>() {
            {
                add(instrument("guitar", "brown"));
                add(instrument("bass", "red"));
                add(instrument("piano", "blue"));
                add(instrument("trumpet", "green"));
                add(instrument("triangle", "yellow"));
            }
        };
    }
}
