package de.ostfalia.prog.ss23.exceptions;

public class FalscheSpielerzahlException extends Exception {
    private final int falscheSpielerAnzahl;

    public FalscheSpielerzahlException(int falscheSpielerAnzahl) {
        this.falscheSpielerAnzahl = falscheSpielerAnzahl;
    }

    public int getFalscheSpielerAnzahl(){
        return falscheSpielerAnzahl;
    }
}
