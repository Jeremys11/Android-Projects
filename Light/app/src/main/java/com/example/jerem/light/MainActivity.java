package com.example.jerem.light;

import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

/*
 *   The objective of this project is to create an app that has a
 *   light that is turned on and off by a switch
 *
 *   One important lesson from this project is learning about Views.
 *   Views require contexts and in this project, the context is "this"
 */

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    // need to create m_lightImageView as class variable to make it in scope
    // for OnCheckedChageListener

    ImageView m_lightImageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // LinearLayout
        // Child class of view - needed to have more than one view
        // Views exist in a tree structure with nodes and leaves
        LinearLayout rootLayout = new LinearLayout(this);
        rootLayout.setBackgroundColor(Color.BLUE);

        // setImageResource takes in an integer representation of elements
        // in the drawable folder using a function called
        // R.<some_folder>.<name_of_resource>

        // The weight option for a new View means that after all other children
        // have been placed, take up as much room that is left

        m_lightImageView = new ImageView(this);
        m_lightImageView.setImageResource(R.drawable.off);
        rootLayout.addView(m_lightImageView,
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        m_lightImageView.setBackgroundColor(Color.YELLOW);

        // rootLayout.addView(View child, LayoutParams params)
        // LinearLayout.LayoutParams(NEED int width, OPTIONAL int height, OPTIONAL float weight)
        // WRAP_CONTENT tells the widget how big it should be

        // To move switch to center of row, you need to use gravity.CENTER_HORIZONTAL
        // To move switch to center of column, you need to use gravity.CENTER_VERTICAL

        Switch lightSwitch = new Switch(this);
        LinearLayout.LayoutParams lightSwitchLayoutParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0);
        lightSwitchLayoutParams.gravity = Gravity.CENTER_VERTICAL;
        rootLayout.addView(lightSwitch, lightSwitchLayoutParams);
        lightSwitch.setBackgroundColor(Color.RED);

        // setContentView
        // OnCreate exists to call setContentView(View view)

        setContentView(rootLayout);

        // OnCheckedChangeListener
        // Requires interface as parameter - need class definition
        // Implement OnCheckedChangeListener on Activity

        lightSwitch.setOnCheckedChangeListener(this);
    }


    // Implementation of onCheckedChangeListener for CompoundButton
    // i.e - the switch

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            m_lightImageView.setImageResource(R.drawable.on);
        }
        else{
            m_lightImageView.setImageResource(R.drawable.off);
        }
    }
}
