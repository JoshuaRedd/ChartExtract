package com.example.chartextract;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

public class ChartController implements View.OnTouchListener {

    ChartModel model;
    InteractionModel iModel;
    private Handler handler = new Handler();


    private int normX, normY;
    private int normDX, normDY;
    private int prevNormX, prevNormY;

    private enum State {READY, RESIZING}

    private State currentState = State.READY;

    public void setModel(ChartModel cModel) {
        model = cModel;
    }

    public void setIModel(InteractionModel im) {
        iModel = im;
    }

    public void setPoints() {
        model.points = iModel.points;
    }

    @Override

    public boolean onTouch(View v, MotionEvent event) {
//        int x = (int)event.getX();
//        int y = (int)event.getY();
//        int index = model.checkHit(x,y);

        normX = (int)event.getX();
        normY = (int)event.getY();
        normDX = normX - prevNormX;
        normDY = normY - prevNormY;
        prevNormX = normX;
        prevNormY = normY;
        int indexStatic = model.checkHit(normX,normY);
        int indexMoved = model.checkHit(normDX,normDY);
        int corner = 0;

        switch (currentState) {
            case READY:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //context: on handle?
                        if (indexStatic != -1) {
                            //side effects
                            System.out.println("Hello");
                            corner = indexStatic;
                            //change state
                            currentState = State.RESIZING;
                        }
                    break;
                }
            case RESIZING:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        iModel.resizeRect(corner,normDX,normDY);
                    case MotionEvent.ACTION_UP:
                        setPoints();
                        currentState = State.READY;
                }

        }
        return true;
    }
}

