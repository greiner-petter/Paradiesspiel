package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Desaster extends Feld {
    public Desaster(Feld davor, int position) {
        super(davor, position);
    }

    @Override
    public void ereignis() {
        Feld feld = this;
        Figur figur = getFigurenAufFeld().get(getFigurenAufFeld().size() - 1);
        for (int wurf : figur.getWurf()) {
            for (int i = 0; i < wurf * 2; i++) {
                feld.figurNachHintenBewegen(figur);
                feld = feld.getDavor();
            }
        }
        feld.ereignis();
    }
}
