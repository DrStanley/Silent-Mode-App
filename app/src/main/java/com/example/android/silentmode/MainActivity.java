package com.example.android.silentmode;

import android.app.Activity;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.example.android.silentmode.RingerHelper;
public class MainActivity extends AppCompatActivity {
    AudioManager audioManager;
    FrameLayout contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

// Get a reference to Android’s AudioManager so we can use
// it to toggle our ringer.
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        // Find the view with the ID "content" in our layout file.
         contentView =
                (FrameLayout) findViewById(R.id.content);
// Create a click listener for the contentView that will toggle
// the phone’s ringer state, and then update the UI to reflect
// the new state.
        contentView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Toggle the ringer mode. If it’s currently normal,
// make it silent. If it’s currently silent,
// do the opposite.
                RingerHelper.performToggle(audioManager);
// Update the UI to reflect the new state
                updateUi();

            }
        });

    }
    /**
     * Updates the UI image to show an image representing silent or
     * normal, as appropriate
     */
    ImageView imageView;
    private void updateUi() {
// Find the view named phone_icon in our layout. We know it’s
// an ImageView in the layout, so downcast it to an ImageView.
        imageView= (ImageView) findViewById(R.id.phone_icon);
// Set phoneImage to the ID of image that represents ringer on
// or off. These are found in res/drawable-xxhdpi
        int phoneImage = RingerHelper.isPhoneSilent(audioManager)
                ? R.drawable.ringer_off
                : R.drawable.ringer_on;
// Set the imageView to the image in phoneImage
        imageView.setImageResource(phoneImage);
    }
    /**
     * Every time the activity is resumed, make sure to update the
     * buttons to reflect the current state of the system (since the
     * user may have changed the phone’s silent state while we were in
     * the background).
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
// Update our UI in case anything has changed.
        updateUi();
    }
}
