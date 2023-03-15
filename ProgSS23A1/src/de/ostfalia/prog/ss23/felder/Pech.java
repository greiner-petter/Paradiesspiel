package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;
import de.ostfalia.prog.ss23.Spieler;
import de.ostfalia.prog.ss23.enums.Feld;

public class Pech extends Felder {
    Feld feld;

    public Pech(){
        feld = Feld.PECH;
    }

    @Override
    public void ereignis(Spieler spieler, Figur figur, int augenzahl) {
        for (int i = 0; i < augenzahl; i++) {
            figur.setPosition(figur.getPosition() - 1);
        }
    }

    @Override
    public Feld getFeld() {
        return feld;
    }
}
