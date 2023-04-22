package de.ostfalia.prog.ss23.exceptions;

import de.ostfalia.prog.ss23.felder.Feld;

public class UngueltigePositionException extends RuntimeException {

    public UngueltigePositionException(String message) {
        super(message);
    }

    public UngueltigePositionException() {
        super("Ungueltige Position");
    }
}
