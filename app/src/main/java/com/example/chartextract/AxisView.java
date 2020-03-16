package com.example.chartextract;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AxisView extends LinearLayout implements ChartListener {
    Bitmap chart;
    Paint myPaint;
    ChartController controller;
    ChartModel model;
    TextView output;
    EditText Y1EditText;
    EditText Y2EditText;
    EditText X1EditText;
    EditText X2EditText;
    InteractionModel iModel;



    public AxisView(Context context) {
        super(context);
        myPaint = new Paint();
        output = new TextView(this.getContext());
        output.setText("0,0");
        output.setTextSize(30);
        output.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        Y1EditText = new EditText(this.getContext());
        Y2EditText = new EditText(this.getContext());
        X1EditText = new EditText(this.getContext());
        X2EditText = new EditText(this.getContext());


        int yAxisMinimumView = View.generateViewId();
        int yAxisMaximumView = View.generateViewId();
        int xAxisMinimumView = View.generateViewId();
        int xAxisMaximumView = View.generateViewId();;


        LinearLayout AxisInfo = new LinearLayout(this.getContext());
        AxisInfo.setOrientation(VERTICAL);

        AxisInfo.addView(CreateAxisSetting("YAxis",yAxisMinimumView,yAxisMaximumView));
        AxisInfo.addView(CreateAxisSetting("XAxis",xAxisMinimumView,xAxisMaximumView));
        AxisInfo.addView(output);
        this.addView(AxisInfo);

//        EditText temp = findViewById(yAxisMaximumView);
//        temp.setText("HURRRRR");

    }

    private LinearLayout CreateAxisSetting(String descriptionStringFirst, int idFirstEdit, int idSecondEdit)
    {
        LinearLayout container = new LinearLayout(this.getContext());
        TextView descriptionFirstEditText = new TextView(this.getContext());
        descriptionFirstEditText.setText(descriptionStringFirst);
        EditText First = new EditText(this.getContext());
        First.setWidth(170);
        TextView to = new TextView(this.getContext());
        to.setText("to");
        EditText Second = new EditText((this.getContext()));
        Second.setWidth(170);

        First.setTextSize(15);
        descriptionFirstEditText.setTextSize(15);
        to.setTextSize(15);
        Second.setTextSize(15);

        First.setId(idFirstEdit);
        Second.setId(idSecondEdit);

        container.addView(descriptionFirstEditText);
        container.addView(First);
        container.addView(to);
        container.addView(Second);
        return container;
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
        this.invalidate();
    }
}
