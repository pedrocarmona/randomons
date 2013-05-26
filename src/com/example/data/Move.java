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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

