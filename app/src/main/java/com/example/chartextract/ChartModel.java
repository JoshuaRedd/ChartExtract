package com.example.chartextract;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;

public class ChartModel {
    Bitmap chartToDraw;
    ArrayList<ChartListener> subscribers;
    ArrayList<Point> points;

    public ChartModel() {
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

    public void addSubscribers(ChartListener subscriber) { subscribers.add(subscriber);}

    public void notifySubscribers() {
        for (ChartListener cl : subscribers){
            cl.modelChanged();
        }
    }

    public int checkHit(float x, float y) {
        for(int n =0;n < points.size();n++){
            if((points.get(n).x >= (x-10) && points.get(n).x <= (x+10)) && (points.get(n).y >= (y-10) && points.get(n).y <= (y+10))){
                return n;
            }
        }
        return -1;
    }

    public void setBitmap(Bitmap newChart){
        this.chartToDraw = newChart;
        notifySubscribers();
    }
}
