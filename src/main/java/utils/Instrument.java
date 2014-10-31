package utils;

public class Instrument {

    private String instrumentType;
    private String colour;

    public Instrument(String instrumentType, String colour) {
        this.instrumentType = instrumentType;
        this.colour = colour;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instrument that = (Instrument) o;

        if (!colour.equals(that.colour)) return false;
        if (!instrumentType.equals(that.instrumentType)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = instrumentType.hashCode();
        result = 31 * result + colour.hashCode();
        return result;
    }
}
