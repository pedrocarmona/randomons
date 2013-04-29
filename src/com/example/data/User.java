package com.example.data;


public class User {

    private long ranking;
    private String name;
    private long points;


    public User(long ranking, String name, long points) {
        this.ranking = ranking;
        this.name = name;
        this.points = points;
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
}
