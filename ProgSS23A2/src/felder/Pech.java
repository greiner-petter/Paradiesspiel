package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Pech extends Feld {
    public Pech(Feld davor, int position) {
        super(davor, position);
    }

    @Override
    public void ereignis() {
        Feld feld = this;
        Figur figur = getFigurenAufFeld().get(getFigurenAufFeld().size() - 1);
        for (int wurf : figur.getWurf()) {
            for (int i = 0; i < wurf; i++) {
                feld.figurNachHintenBewegen(figur);
                feld = feld.getDavor();
            }
        }
        feld.ereignis();
    }
}
