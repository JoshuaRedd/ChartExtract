package com.example.chartextract;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ChartModel model;
    private InteractionModel iModel;
    private ChartView chartView;
    private AxisView axisView;
    private DetailView detailView;
    private ChartController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout root = findViewById(R.id.root);
        LinearLayout detailMenu = new LinearLayout(this);

        // MVC
        model = new ChartModel();
        iModel = new InteractionModel();
        chartView = new ChartView(this);
        detailView = new DetailView(this);
        axisView = new AxisView(this);
        controller = new ChartController();

        // Connect MVC //
        controller.setModel(model);
        controller.setIModel(iModel);

        //chartView
        chartView.setModel(model);
        chartView.setiModel(iModel);
        chartView.setController(controller);
        //detailView
        detailView.setModel(model);
        detailView.setiModel(iModel);
        detailView.setController(controller);
        //axisView
        axisView.setModel(model);
        axisView.setiModel(iModel);
        axisView.setController(controller);

        //add model subscribers
        model.addSubscribers(chartView);
        model.addSubscribers(detailView);
        model.addSubscribers(axisView);

        iModel.addSubscribers(chartView);
        iModel.addSubscribers(detailView);
        iModel.addSubscribers(axisView);


        // Setup detailMenu and add detailView and AxisView
        detailMenu.setOrientation(LinearLayout.HORIZONTAL);
        detailMenu.addView(detailView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,2f));
        detailMenu.addView(axisView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,2f));


        // Add Views to root
        root.addView(detailMenu,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,7f));

        root.addView(chartView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,2f));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        // return true so that the main_menu pop up is opened
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.chooseChart:
                System.out.println("Chart Choosen");
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a chart"), 123);
                return true;

            case R.id.screenshot:
                System.out.println("Screenshot");
                return true;

            case R.id.exportData:
                System.out.println("Export Data");
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData(); //The uri with the location of the image
            try {
                Bitmap chart = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                model.setBitmap(chart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
