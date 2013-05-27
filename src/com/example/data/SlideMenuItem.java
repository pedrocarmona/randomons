package com.example.data;

public class SlideMenuItem    {

    public int slideIcon;
    public String slideText;

    public SlideMenuItem(){
        super();
    }

    public SlideMenuItem(int slideIcon, String slideText) {
        super();
        this.slideIcon = slideIcon;
        this.slideText = slideText;
    }

    public int getSlideIcon() {
        return slideIcon;
    }

    public void setSlideIcon(int slideIcon) {
        this.slideIcon = slideIcon;
    }

    public String getSlideText() {
        return slideText;
    }

    public void setSlideText(String slideText) {
        this.slideText = slideText;
    }
}
