package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;
import de.ostfalia.prog.ss23.Spieler;
import de.ostfalia.prog.ss23.enums.Feld;

public abstract class Felder {
    public abstract void ereignis(Spieler spieler, Figur figur, int augenzahl);
    public abstract Feld getFeld();
}
