package de.ostfalia.prog.ss23;

public class Figur {
    private int position;
    private String name;
    private boolean paradies;

    public Figur(String name) {
        setPosition(0);
        setName(name);
        setParadies(false);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParadies(Boolean paradies) {
        this.paradies = paradies;
    }

    public boolean getParadies() {
        return paradies;
    }
}
