package com.example.data;

public class Event {

    private String date;
    private String description;
    private int evtPicId;

    public Event(String date, String description, int evtPicId) {
        this.date = date;
        this.description = description;
        this.evtPicId = evtPicId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEvtPicId() {
        return evtPicId;
    }

    public void setEvtPicId(int evtPicId) {
        this.evtPicId = evtPicId;
    }
}
