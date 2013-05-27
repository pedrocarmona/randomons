package com.example.data;


public abstract class Item {

    protected int typeImgId;
    protected String name;
    protected String description;

    public Item(int typeImgId, String name, String description) {
        this.typeImgId = typeImgId;
        this.name = name;
        this.description = description;
    }

    public int getType() {
        return typeImgId;
    }

    public void setType(int type) {
        this.typeImgId = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

