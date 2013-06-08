package com.example.data;


import java.util.ArrayList;

public class Player {

    private long ranking;
    private String name;
    private long points;
    private int playerImg;
    private ArrayList<Randomon> randomonCollection;
    private ArrayList<CloseEvent> lastEvents;


    public Player(long ranking, String name, long points, int playerImg) {
        this.ranking = ranking;
        this.name = name;
        this.points = points;
        this.playerImg = playerImg;
        this.lastEvents = new ArrayList<CloseEvent>();
        this.randomonCollection = new ArrayList<Randomon>();
    }

    public void addRandomon(Randomon r) {

        this.randomonCollection.add(r);
    }


    public long getRanking() {
        return ranking;
    }

    public void setRanking(long ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public int getPlayerImg() {
        return playerImg;
    }

    public void setPlayerImg(int playerImg) {
        this.playerImg = playerImg;
    }

    public ArrayList<Randomon> getRandomonCollection() {
        return randomonCollection;
    }

    public void setRandomonCollection(ArrayList<Randomon> randomonCollection) {
        this.randomonCollection = randomonCollection;
    }

    public ArrayList<CloseEvent> getLastEvents() {
        return lastEvents;
    }

    public void setLastEvents(ArrayList<CloseEvent> lastEvents) {
        this.lastEvents = lastEvents;
    }
}
