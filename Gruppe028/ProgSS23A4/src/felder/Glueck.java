package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Glueck extends Feld {
    public Glueck(Feld davor, int position) {
        super(davor, position);
    }

    /**
     * Bewegt die Figur um den Wurf nochmal nach vorne
     */
    @Override
    public void ereignis() {
        Feld feld = this;
        Figur figur = getFigurenAufFeld().get(getFigurenAufFeld().size() - 1);
        for (int wurf : figur.getWurf()) {
            for (int i = 0; i < wurf; i++) {
                feld.figurNachVorneBewegen(figur);
                feld = feld.getDanach();
            }
        }
        feld.ereignis();
    }

    @Override
    public String toString() {
        return "Glueck";
    }
}
