package de.ostfalia.prog.ss23.exceptions;

import de.ostfalia.prog.ss23.felder.Feld;

public class UngueltigePositionException extends RuntimeException {
    private final Feld ungueltigesFeld;
    private final int ungueltigePosition;

    public UngueltigePositionException(Feld ungueltigesFeld) {
        this.ungueltigesFeld = ungueltigesFeld;
        this.ungueltigePosition = ungueltigesFeld.getPosition();
    }

    public Feld getUngueltigesFeld() {
        return ungueltigesFeld;
    }

    public int getUngueltigePosition() {
        return ungueltigePosition;
    }
}
