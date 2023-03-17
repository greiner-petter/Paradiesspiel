package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;
import de.ostfalia.prog.ss23.Spieler;
import de.ostfalia.prog.ss23.enums.Feld;

public class Glueck extends Felder {
    Feld feld;

    public Glueck(){
        feld = Feld.GLUECK;
    }

    @Override
    public void ereignis(Spieler spieler, Figur figur) {
        figur.setPosition(figur.getPosition() + 6);
    }

    @Override
    public Feld getFeld() {
        return feld;
    }
}
