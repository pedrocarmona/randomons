package com.example.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Randomon implements Serializable
{
    private String name;
    private String type;
    private int hitpoints;
    private int attack;
    private int defense;
    private int speed;
    private double growth;
    private int level;
    private int current_hitpoints;
    private int current_experience;
    private String status;
    private String description;
    private int preference;
    private int picId;
    private Player player;
    private ArrayList<Move> moves;

    public Randomon(String name, String type, int attack, int defense, int speed, double growth, int hitpoints,
                    int level, int current_hitpoints, int current_experience, String status, String description, int picId
                    , int creatureId,  ArrayList<Move> moves) {
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.growth = growth;
        this.hitpoints = hitpoints;
        this.level = level;
        this.current_hitpoints = current_hitpoints;
        this.current_experience = current_experience;
        this.status = status;
        this.description = description;
        this.picId = picId;
        this.moves = moves;

    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getGrowth() {
        return growth;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrent_hitpoints() {
        return current_hitpoints;
    }

    public void setCurrent_hitpoints(int current_hitpoints) {
        this.current_hitpoints = current_hitpoints;
    }

    public int getCurrent_experience() {
        return current_experience;
    }

    public void setCurrent_experience(int current_experience) {
        this.current_experience = current_experience;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }

    public Player getUser() {
        return player;
    }

    public void setUser(Player user) {
        this.player = user;
    }
}
