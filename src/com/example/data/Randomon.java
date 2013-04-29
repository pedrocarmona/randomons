package com.example.data;

public class Randomon
{
    private String name;
    private int lvl;

    public Randomon(String name, int lvl) {
        this.name = name;
        this.lvl = lvl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
