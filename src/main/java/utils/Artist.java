package utils;

import java.util.List;

public class Artist {

    private List<Instrument>  instruments;
    private String name;
    private String origin;
    private boolean isFrom;

    public Artist() {
    }

    public Artist(String name, String origin) {
        this.name = name;
        this.origin = origin;
    }

    public String getName(){return name;}

    public String getOrigin() {return origin;}

    public boolean isFrom(String uk) {
        return false;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
