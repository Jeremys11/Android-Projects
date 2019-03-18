package com.example.jerem.amp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity
        implements KnobView.OnAngleChangedListener /***************************/{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout rootLayout = new LinearLayout(this);

        KnobView volumeKnob = new KnobView(this);
        volumeKnob.setBackgroundColor(Color.GRAY);
        volumeKnob.setOnAngleChangedListener(this); /*************************/
        rootLayout.addView(volumeKnob, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));

        KnobView bassKnob = new KnobView(this);
        bassKnob.setBackgroundColor(Color.RED);
        bassKnob.setOnAngleChangedListener(this); /***************************/
        rootLayout.addView(bassKnob, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));

        KnobView fadeKnob = new KnobView(this);
        fadeKnob.setBackgroundColor(Color.BLUE);
        fadeKnob.setOnAngleChangedListener(this); /***************************/
        rootLayout.addView(fadeKnob, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));

        // Can set Padding for a view which denotes area that
        // noting will be drawn in
        //knob.setPadding(20,20,20,20);
        //knob.setTheta((float)Math.PI * 1.25f);

        setContentView(rootLayout);
    }

    /***************************/
    /***************************/
    /***************************/
    public void onAngleChanged(float theta) {
        float volume = theta;
        Log.i("TAG", "Volume changed to: " + volume);
    }
}
