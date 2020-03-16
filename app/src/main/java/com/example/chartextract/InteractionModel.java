package com.example.chartextract;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

public class InteractionModel {
    ArrayList<ChartListener> subscribers;
    float viewWidth, viewHeight;
    ArrayList<Point> points;


    public InteractionModel() {
        subscribers = new ArrayList<>();
        points = new ArrayList<>();
        Point point1 = new Point(150, 180);
        Point point2 = new Point(500, 180);
        Point point3 = new Point(150, 500);
        Point point4 = new Point(500, 500);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
    }

    public void setViewSize(float width, float height) {
        viewWidth = width;
        viewHeight = height;
    }

    public void resizeRect(int index,int x, int y){
        points.get(index).x = x;
        points.get(index).y = y;
    }

    public void addSubscribers(ChartListener subscriber) { subscribers.add(subscriber);}

    public void notifySubscribers() {
        for (ChartListener cl : subscribers){
            cl.modelChanged();
        }
    }
}
