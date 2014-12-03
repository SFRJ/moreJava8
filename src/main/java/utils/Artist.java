package utils;

import java.util.List;
import java.util.stream.Stream;

public class Artist {

    private List<Instrument>  instruments;
    private String name;
    private String origin;
    private boolean isFrom;
    private Stream<Artist> members;
    private boolean isSolo;

    public Artist() {
    }

    public Artist(String name, String origin) {
        this.name = name;
        this.origin = origin;
    }

    public Artist(String name, String origin, List<Instrument> instruments) {
        this.name = name;
        this.origin = origin;
        this.instruments = instruments;
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

    public Stream<Artist> getMembers() {
        return members;
    }

    public boolean isSolo() {
        return isSolo;
    }

    public void setSolo(boolean isSolo) {
        this.isSolo = isSolo;
    }
}
