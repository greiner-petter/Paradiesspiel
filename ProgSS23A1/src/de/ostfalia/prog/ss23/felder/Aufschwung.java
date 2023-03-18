package de.ostfalia.prog.ss23.felder;

public class Aufschwung extends Feld {
    public Aufschwung(Feld davor) {
        super(davor);
    }

    @Override
    public void ereignis() {

    }

    @Override
    public String toString() {
        return "Aufschwung";
    }
}
