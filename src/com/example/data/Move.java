package com.example.data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: pedrocarmona
 * Date: 25/05/13
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public class Move implements Serializable{
    private String name;
    private int animationPath;
    private int damage;
    private int attack;
    private int accuracy;
    private int status;
    private double status_probability;
    private String description;
    private int type;




    /*public Move(String name, String animationPath) {
        this.name = name;
        this.animationPath = animationPath;
    } */

    public Move(String name, int animationPath, int attack, int accuracy, int status, double status_probability, String description, int type) {
        this.name = name;
        this.animationPath = animationPath;
        this.attack = attack;
        this.accuracy = accuracy;
        this.status = status;
        this.status_probability = status_probability;
        this.description = description;
        this.type = type;
    }

    public Move(String name, int animationPath, int damage) {
        this.name = name;
        this.animationPath = animationPath;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnimationPath() {
        return animationPath;
    }

    public void setAnimationPath(int animationPath) {
        this.animationPath = animationPath;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getStatus_probability() {
        return status_probability;
    }

    public void setStatus_probability(double status_probability) {
        this.status_probability = status_probability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

