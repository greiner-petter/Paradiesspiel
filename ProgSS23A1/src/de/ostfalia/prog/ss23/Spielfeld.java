package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Feld;
import de.ostfalia.prog.ss23.felder.*;

public class Spielfeld {
    private final int groesse;
    private final Felder[] felder;

    public Spielfeld(int groesse) {
        this.groesse = groesse;
        felder = new Felder[groesse];
        for (int i = 0; i < felder.length; i++) {
            felder[i] = new Standard();
        }
        for (Feld feld : Feld.values()) {
            switch (feld) {
                case START -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Start();
                    }
                }
                case GLUECK -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Glueck() {
                        };
                    }
                }
                case PARADIES -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Paradies() {
                        };
                    }
                }
            }
        }
    }

    public Felder[] getFelder() {
        return felder;
    }

    public int getGroesse() {
        return groesse;
    }
}
