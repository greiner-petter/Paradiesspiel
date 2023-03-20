package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Glueck extends Feld {
    public Glueck(Feld davor, int position) {
        super(davor, position);
    }

    @Override
    public void ereignis() {
        Feld feld = this;
        Figur figur = getFigurenAufFeld().get(getFigurenAufFeld().size() - 1);
        for (int i = 0; i < 6; i++) {
            feld.figurNachVorneBewegen(figur);
            feld = feld.getDanach();
        }
    }

    @Override
    public String toString() {
        return "Glueck";
    }
}
