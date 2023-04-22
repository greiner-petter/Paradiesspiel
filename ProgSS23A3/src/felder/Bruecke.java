package de.ostfalia.prog.ss23.felder;

import de.ostfalia.prog.ss23.Figur;

public class Bruecke extends Feld {
    public Bruecke(Feld davor, int position) {
        super(davor, position);
    }

    /**
     * Bewegt die Figur 6 Felder nach vorne, wenn diese auf dem Feld landet
     */
    @Override
    public void ereignis() {
        Figur figur = getFigurenAufFeld().get(getFigurenAufFeld().size() - 1);
        getDanach().figurAufFeldSetzen(figur);
        figurVonFeldEntfernen(figur);
        figur.setPosition(figur.getPosition() + 6);
    }

    /**
     * Bewegt die Figur 6 Felder nach vorne, wenn diese über das Feld zieht
     *
     * @param figur die Figur, die bewegt wird
     * @return ob die Figur sich nach vorne bewegen kann
     */
    @Override
    public boolean figurNachVorneBewegen(Figur figur) {
        getDanach().getDanach().figurAufFeldSetzen(figur);
        figurVonFeldEntfernen(figur);
        figur.setPosition(figur.getPosition() + 7);
        return true;
    }

    @Override
    public String toString() {
        return "Bruecke";
    }
}
