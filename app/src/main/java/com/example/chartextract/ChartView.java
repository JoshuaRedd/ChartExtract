package com.example.chartextract;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;

public class ChartView extends View implements ChartListener{
    Bitmap chart;
    Paint myPaint;
    Paint circlePaint;
    Rect sourceRect;
    Rect destinationRect;
    Rect SelectionRect;
    ChartController controller;
    ChartModel model;
    InteractionModel iModel;

    public ChartView(Context context) {
        super(context);
        this.myPaint = new Paint();
        this.circlePaint = new Paint();

    }

    public void setModel(ChartModel model) {
        this.model =model;
    }

    public void setiModel(InteractionModel iModel) {
        this.iModel = iModel;
    }

    public void setController(ChartController newController) {
        controller = newController;
        this.setOnTouchListener(controller);
    }

    @Override
    public void modelChanged() {
        this.invalidate();
    }

    public void onDraw(Canvas c) {
        if(model.chartToDraw == null){
            return;
        }
        else {
            sourceRect = new Rect(0,0,model.chartToDraw.getWidth(),model.chartToDraw.getHeight());
            destinationRect = new Rect(0,0,getRight(),getBottom()-400);
            SelectionRect = new Rect(model.points.get(0).x,model.points.get(0).y,model.points.get(3).x,model.points.get(3).y);

            myPaint.setStyle(Paint.Style.STROKE);
            myPaint.setColor(Color.RED);
            myPaint.setStrokeWidth(3);
            circlePaint.setColor(Color.RED);
            circlePaint.setStyle(Paint.Style.FILL);
            // the following draws the bitmap into the destination rectangle
            c.drawBitmap(model.chartToDraw, sourceRect, destinationRect, myPaint);
            c.drawRect(SelectionRect.left,SelectionRect.top,SelectionRect.right,SelectionRect.bottom,myPaint);
            for(int n=0;n<model.points.size();n++){
                c.drawCircle(model.points.get(n).x,model.points.get(n).y,20,circlePaint);
            }
        }
    }
}
