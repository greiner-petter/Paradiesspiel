package de.ostfalia.prog.ss23.exceptions;

public class DateiLeerException extends RuntimeException {
    public DateiLeerException(String message) {
        super(message);
    }

    public DateiLeerException() {
        super("Die Datei ist leer");
    }
}
