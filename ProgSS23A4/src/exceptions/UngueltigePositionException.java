package de.ostfalia.prog.ss23.exceptions;

public class UngueltigePositionException extends RuntimeException {

    public UngueltigePositionException(String message) {
        super(message);
    }

    public UngueltigePositionException() {
        super("Ungueltige Position");
    }
}
