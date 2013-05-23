package com.example.others;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

class Line {

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
}

public class Drawer extends View
{  
    ArrayList<Line> lines;
    public Drawer(Context context)
    {
        super(context);
        lines = new ArrayList<Line>();
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        Line newLine = new Line(x1, y1, x2, y2);
        lines.add(newLine);
    }

    public void onDrawe(Canvas canvas)
    {
        Paint p = new Paint();
        p.setStrokeWidth(21);
        p.setStyle(Paint.Style.FILL);
        p.setColor(android.graphics.Color.BLACK);
        for (Line myLine : lines) {
            canvas.drawLine(myLine.getX1(), myLine.getY1(), myLine.getX2(), myLine.getY2(), p);
        }
        invalidate();
    }
}