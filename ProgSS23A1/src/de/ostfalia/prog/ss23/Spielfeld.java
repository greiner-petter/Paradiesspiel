package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Feld;
import de.ostfalia.prog.ss23.felder.*;

public class Spielfeld {
    private final Felder[] felder;

    public Spielfeld(int groesse) {
        felder = new Felder[groesse];
        for (int i = 0; i < felder.length; i++) {
            felder[i] = new Standard();
        }
        felder[0] = new Start();
        felder[groesse - 1] = new Paradies();
        for (Feld feld : Feld.values()) {
            switch (feld) {
                case GLUECK -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Glueck();
                    }
                }
                case BRUECKE -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Bruecke();
                    }
                }
                case PECH -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Pech();
                    }
                }
                case DESASTER -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Desaster();
                    }
                }
                case NEUANFANG -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Neuanfang();
                    }
                }
                case AUFSCHWUNG -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Aufschwung();
                    }
                }
                case LABYRINTH -> {
                    for (int i : feld.getPositions()) {
                        felder[i] = new Labyrinth();
                    }
                }
                default -> {

                }
            }
        }
    }

    public Felder[] getFelder() {
        return felder;
    }

    public int getGroesse() {
        return felder.length;
    }
}
