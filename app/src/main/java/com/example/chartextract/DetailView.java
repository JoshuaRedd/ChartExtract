package com.example.chartextract;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;

public class DetailView extends View implements ChartListener {
    Bitmap chart;
    Paint myPaint;
    Rect sourceRect;
    Rect destinationRect;
    ChartController controller;
    ChartModel model;
    InteractionModel iModel;


    public DetailView(Context context) {
        super(context);
        myPaint = new Paint();
    }

    public void setModel(ChartModel model) {
        this.model = model;
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
        sourceRect = new Rect(0,0,model.chartToDraw.getWidth(),model.chartToDraw.getHeight());
        destinationRect = new Rect(0,0,getRight(),getBottom());
        this.invalidate();
    }

    public void onDraw(Canvas c) {
        if(model.chartToDraw == null){
            return;
        }
        else {
            // the following draws the bitmap into the destination rectangle
            c.drawBitmap(model.chartToDraw, sourceRect, destinationRect, myPaint);
        }
    }
}