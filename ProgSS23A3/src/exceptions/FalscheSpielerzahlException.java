package de.ostfalia.prog.ss23.exceptions;

public class FalscheSpielerzahlException extends Exception {
    public FalscheSpielerzahlException(String message) {
        super(message);
    }

    public FalscheSpielerzahlException() {
        super("Falsche Anzahl Spieler");
    }
}
