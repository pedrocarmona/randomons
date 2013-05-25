package com.example.data;

/**
 * Created with IntelliJ IDEA.
 * User: pedrocarmona
 * Date: 25/05/13
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public class Move {
    private String name;
    private String animationPath;

    public Move(String name, String animationPath) {
        this.name = name;
        this.animationPath = animationPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimationPath() {
        return animationPath;
    }

    public void setAnimationPath(String animationPath) {
        this.animationPath = animationPath;
    }
}

