package de.ostfalia.prog.ss23;

public class Figur {
    private int position;
    private String name;

    public Figur(String name) {
        setPosition(0);
        setName(name);
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
}
