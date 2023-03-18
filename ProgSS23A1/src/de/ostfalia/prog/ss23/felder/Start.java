package de.ostfalia.prog.ss23.felder;

public class Start extends Feld {
    public Start(Feld davor) {
        super(davor);
    }

    @Override
    public void ereignis() {}

    @Override
    public String toString() {
        return "Start";
    }
}
