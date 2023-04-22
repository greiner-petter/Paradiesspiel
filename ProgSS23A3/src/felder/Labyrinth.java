package de.ostfalia.prog.ss23.felder;

public class Labyrinth extends Feld{
    public Labyrinth(Feld davor, int position) {
        super(davor, position);
    }
    @Override
    public void ereignis() {
        getFigurenAufFeld().get(getFigurenAufFeld().size() - 1).getSpieler().setAussetzen(true);
    }
}
