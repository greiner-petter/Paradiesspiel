package de.ostfalia.prog.ss23.enums;

public enum Feld {
    PECH(5, 9),
    BRUECKE(6),
    GLUECK(14, 18, 27, 32, 36, 50),
    LABYRINTH(19),
    DESASTER(24, 41, 54),
    AUFSCHWUNG(52),
    NEUANFANG(58),
    START(0),
    PARADIES(63),
    STANDARD;

    private final int[] positions;

    Feld(int... positions) {
        this.positions = positions;
    }

    public int[] getPositions() {
        return positions;
    }
}
