package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.exceptions.*;
import de.ostfalia.prog.ss23.felder.*;
import de.ostfalia.prog.ss23.interfaces.*;

import java.io.*;
import java.util.Arrays;

public class Paradiesspiel implements IParadiesspiel, ISpeicherbar {
    protected Spieler[] mitspieler;
    protected Feld[] spielfeld;
    private Farbe farbeAmZug;

    /**
     * Konstruktor erstellt die Spieler und das Spielfeld und setzt alle Figuren der Spieler auf das Startfeld
     *
     * @param farben Farben der Spieler
     */
    public Paradiesspiel(Farbe... farben) throws FalscheSpielerzahlException {
        if (2 > farben.length || farben.length > 6) {
            throw new FalscheSpielerzahlException(Integer.toString(farben.length));
        }
        mitspielerErstellen(farben);
        spielfeldErstellen();
        for (Spieler spieler : mitspieler) {
            for (Figur figur : spieler.getFiguren()) {
                spielfeld[0].figurAufFeldSetzen(figur);
                figur.setPosition(0);
            }
        }
    }

    /**
     * Konstruktor erstellt die Spieler und das Spielfeld und setzt die in conf angegebenen Figuren auf die jeweiligen
     * Felder und den rest der Figuren der Spieler auf das Startfeld
     *
     * @param conf Bestimmte Figuren mit Position
     * @param farben Farben der Spieler
     */
    public Paradiesspiel(String conf, Farbe... farben) throws FalscheSpielerzahlException, UngueltigePositionException {
        if (2 > farben.length || farben.length > 6) {
            throw new FalscheSpielerzahlException(Integer.toString(farben.length));
        }
        mitspielerErstellen(farben);
        spielfeldErstellen();
        String[] confSplit;
        String[] figurUndPosition;
        confSplit = conf.split(",");
        for (String config : confSplit) {
            config = config.trim();
            figurUndPosition = config.split(":");
            Figur figur = getFigur(figurUndPosition[0]);
            int position = Integer.parseInt(figurUndPosition[1]);
            if (illegalePosition(position)) {
                throw new UngueltigePositionException(Integer.toString(position));
            }
            spielfeld[position].figurAufFeldSetzen(figur);
            figur.setPosition(position);
        }
        for (Spieler spieler : mitspieler) {
            for (Figur figur : spieler.getFiguren()) {
                if (figur.getPosition() == 0) {
                    spielfeld[0].figurAufFeldSetzen(figur);
                }
            }
        }
    }

    public void mitspielerErstellen(Farbe... farben) {
        this.mitspieler = new Spieler[farben.length];
        for (int i = 0; i < farben.length; i++) {
            this.mitspieler[i] = new Spieler(farben[i], 2);
        }
    }

    /**
     * Erstellt das Spielfeld
     */
    public void spielfeldErstellen() {
        spielfeld = new Feld[64];
        spielfeld[0] = new Start(null, 0);
        Feld davor = spielfeld[0];
        for (int i = 1; i < spielfeld.length; i++) {
            if (i == spielfeld.length - 1) {
                spielfeld[i] = new Paradies(davor, i);
            } else if (Arrays.asList(14, 18, 27, 32, 36, 50).contains(i)) {
                spielfeld[i] = new Glueck(davor, i);
            } else if (i == 6) {
                spielfeld[i] = new Bruecke(davor, i);
            } else if (i == 52) {
                spielfeld[i] = new Aufschwung(davor, i);
            } else if (i == 5 || i == 9) {
                spielfeld[i] = new Pech(davor, i);
            } else if (Arrays.asList(24, 41, 54).contains(i)) {
                spielfeld[i] = new Desaster(davor, i);
            } else if (i == 58) {
                spielfeld[i] = new Neuanfang(davor , i);
            } else if (i == 19) {
                spielfeld[i] = new Labyrinth(davor , i);
            } else {
                spielfeld[i] = new Standard(davor, i);
            }
            davor = spielfeld[i];
            spielfeld[i-1].setDanach(davor);
        }
        spielfeld[6].setDanach(spielfeld[12]);
    }

    @Override
    public Farbe getFarbeAmZug() {
        return farbeAmZug;
    }

    @Override
    public void setFarbeAmZug(Farbe farbe) {
        this.farbeAmZug = farbe;
    }

    /**
     * Gibt die Position der übergebenen Figur zurück, falls diese nicht existiert wird -1 zurückgegeben
     *
     * @param figur Der Name der gesuchten Figur (z.B. "BLAU-A")
     * @return Die Position der Figur
     */
    @Override
    public int getFigurposition(String figur) {
        for (Spieler spieler : mitspieler) {
            if (spieler.getFigur(figur) != null){
                return spieler.getFigur(figur).getPosition();
            }
        }
        return -1;
    }

    /**
     * Bewegt die übergebene Figur um die übergebenen Würfe
     *
     * @param figur       Der Name der Figur, welche bewegt werden soll (z.B. "BLAU-A")
     * @param augenzahlen Eine (oder mehrere) zufällig gewürfelte Zahl(en)
     * @return ob die figur bewegt werden konnte
     */
    @Override
    public boolean bewegeFigur(String figur, int... augenzahlen) {
        if (getFigurposition(figur) == -1 ||
                !figur.contains(getFarbeAmZug().toString()) ||
                    getGewinner() != null ||
                        getFigur(figur).getPosition() == spielfeld.length - 1 ||
                            getSpieler(farbeAmZug).getAussetzen()) {
            getSpieler(farbeAmZug).setAussetzen(false);
            return false;
        }
        boolean kannNachVorne = true;
        getFigur(figur).setWurf(augenzahlen);
        for (int augenzahl : augenzahlen) {
            if (getFigur(figur).getParadies()) {
                getFigur(figur).setParadies(false);
                kannNachVorne = false;
            }
            for (int i = 0; i < Math.abs(augenzahl); i++) {
                if (kannNachVorne) {
                    kannNachVorne = spielfeld[getFigurposition(figur)].figurNachVorneBewegen(getFigur(figur));
                } else {
                    spielfeld[getFigurposition(figur)].figurNachHintenBewegen(getFigur(figur));
                }
            }
        }
        spielfeld[getFigurposition(figur)].ereignis();
        return true;
    }

    /**
     * Gibt zurück die Farbe des gewinners, falls noch keiner gewonnen hat, wird null zurückgegeben
     *
     * @return den Gewinner
     */
    @Override
    public Farbe getGewinner() {
        for (Spieler spieler : mitspieler) {
            if (spieler.istGewinner()) {
                return spieler.getFarbe();
            }
        }
        return null;
    }

    /**
     * @return alle Spieler als Farben-Liste
     */
    @Override
    public Farbe[] getAlleSpieler() {
        Farbe[] alleSpieler = new Farbe[mitspieler.length];
        int i = 0;
        for (Spieler spieler : mitspieler) {
            alleSpieler[i] = spieler.getFarbe();
            i++;
        }
        return alleSpieler;
    }

    /**
     * @param farbe die Farbe des gesuchten Spielers
     * @return den Spieler dessen Farbe übergeben wurde als Objekt
     */
    public Spieler getSpieler(Farbe farbe) {
        for (Spieler spieler : mitspieler) {
            if (spieler.getFarbe().equals(farbe)) {
                return spieler;
            }
        }
        return null;
    }

    /**
     * @param figur die Figur als String
     * @return die Figur als Objekt
     */
    public Figur getFigur(String figur) {
        for (Spieler spieler: mitspieler) {
            if (spieler.getFigur(figur) != null) {
                return spieler.getFigur(figur);
            }
        }
        return null;
    }

    public boolean illegalePosition(int position) {
        return Arrays.asList(5, 6, 9, 14, 18, 24, 27, 32, 36, 41, 50, 54, 58).contains(position) ||
                position < 0 ||
                position > spielfeld.length;
    }

    public Feld[] getSpielfeld() {
        return spielfeld;
    }

    public Spieler[] getMitspieler() {
        return mitspieler;
    }

    public String toString() {
        return "Paradiesspiel";
    }

    @Override
    public void speichern(String dateiName) throws IOException {
            FileWriter writer = new FileWriter(dateiName);
            StringBuilder builder = new StringBuilder();
            if (this instanceof ParadiesspielSommer) {
                builder.append("1;");
            } else {
                builder.append("0;");
            }
            for (Spieler spieler : mitspieler) {
                for (Figur figur : spieler.getFiguren()) {
                    if (figur.getPosition() != 0) {
                        builder.append(figur.getName());
                        builder.append(":");
                        builder.append(figur.getPosition());
                        builder.append(",");
                    }
                }
            }
            builder.setCharAt(builder.length() - 1, ';');
            for (Spieler spieler : mitspieler) {
                builder.append(spieler.getFarbe().toString());
                builder.append(",");
            }
            builder.setCharAt(builder.length() - 1, ';');
            for (Spieler spieler : mitspieler) {
                builder.append(spieler.getAussetzen());
                builder.append(",");
            }
            builder.setCharAt(builder.length() - 1, ';');
            writer.write(builder.toString());
            writer.close();
    }


    public static IParadiesspiel laden(String dateiName) throws DateiLeerException, IOException, FalscheSpielerzahlException {
        BufferedReader reader = new BufferedReader(new FileReader(dateiName));
        String input = reader.readLine();
        if (input == null) {
            throw new DateiLeerException();
        }
        String[] splitInput = input.split(";");
        String[] splitFarben = splitInput[2].split(",");
        Farbe[] farben = new Farbe[splitFarben.length];
        for (int i = 0; i < splitFarben.length; i++) {
            farben[i] = Farbe.valueOf(splitFarben[i].trim());
        }
        String[] aussetzen = splitInput[3].split(",");
        if (Integer.parseInt(splitInput[0]) == 1) {
            Paradiesspiel spiel = new ParadiesspielSommer(splitInput[1], farben);
            for (int i = 0; i < aussetzen.length; i++) {
                spiel.getSpieler(spiel.getAlleSpieler()[i]).setAussetzen(Boolean.parseBoolean(aussetzen[i].trim()));
            }
            return spiel;
        } else {
            Paradiesspiel spiel = new Paradiesspiel(splitInput[1], farben);
            for (int i = 0; i < aussetzen.length; i++) {
                spiel.getSpieler(spiel.getAlleSpieler()[i]).setAussetzen(Boolean.parseBoolean(aussetzen[i].trim()));
            }
            return spiel;
        }
    }
}
