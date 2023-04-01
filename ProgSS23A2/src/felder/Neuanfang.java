package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Neuanfang extends Feld{
    public Neuanfang(Feld davor, int position) {
        super(davor, position);
    }

    @Override
    public void ereignis() {
        Feld feld = this;
        Figur figur = getFigurenAufFeld().get(getFigurenAufFeld().size() - 1);
        while (feld.getDavor() != null) {
            feld.figurNachHintenBewegen(figur);
            feld = feld.getDavor();
        }
    }
}
