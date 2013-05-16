package com.example.data;

public class CloseEvent {

    private int closeEventType;
    private String closeEventDesc;

    public CloseEvent(int closeEventType, String closeEventDesc) {
        this.closeEventType = closeEventType;
        this.closeEventDesc = closeEventDesc;
    }

    public int getCloseEventType() {
        return closeEventType;
    }

    public void setCloseEventType(int closeEventType) {
        this.closeEventType = closeEventType;
    }

    public String getCloseEventDesc() {
        return closeEventDesc;
    }

    public void setCloseEventDesc(String closeEventDesc) {
        this.closeEventDesc = closeEventDesc;
    }

}
