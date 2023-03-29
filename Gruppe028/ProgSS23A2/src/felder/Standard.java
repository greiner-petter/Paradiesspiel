package de.ostfalia.prog.ss23.felder;

public class Standard extends Feld {
    public Standard(Feld davor, int position) {
        super(davor, position);
    }

    @Override
    public void ereignis() {}

    @Override
    public String toString() {
        return "-";
    }
}
