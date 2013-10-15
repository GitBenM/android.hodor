package com.benm.hodor;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;

public class MainActivity extends Activity {
    private List<ButtonSound> buttonSounds;

    protected void onDestroy(Bundle savedInstanceState){
        buttonSounds = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Populate an ArrayList with the sounds of Hodor!
        buttonSounds = getSounds();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ScrollView scrollView = (ScrollView)findViewById(R.id.scroll);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for(ButtonSound bs : buttonSounds)
        {
            Button b = new Button(this);
            b.setText(bs.Name);
            b.setId(bs.Id);
            b.setOnClickListener(buttonClickHandler);
            linearLayout.addView(b);
        }
        scrollView.addView(linearLayout);
    }

    OnClickListener buttonClickHandler = new OnClickListener() {
        @Override
        public void onClick(View view) {
            ButtonSound bs = null;

            //Get the button sound corresponding to the button pressed.
            for(ButtonSound t : buttonSounds) if (t.Id == view.getId()) bs = t;

            // If no button sound is found, return early.
            if(bs == null) return;

            MediaPlayer m = MediaPlayer.create(MainActivity.this, bs.SoundId);
            m.start();
            m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            });
            bs = null;
        }
    };



    private List<ButtonSound> getSounds(){
        List<ButtonSound> bs = new ArrayList<ButtonSound>();
        bs.add(new ButtonSound(1, "Annoyed", R.raw.annoyed));
        bs.add(new ButtonSound(2, "Energetic", R.raw.energetic));
        bs.add(new ButtonSound(3, "Happy", R.raw.happy));
        bs.add(new ButtonSound(4, "Normal", R.raw.normal));
        bs.add(new ButtonSound(5, "Question", R.raw.question));
        bs.add(new ButtonSound(6, "Ready", R.raw.ready));
        bs.add(new ButtonSound(7, "Scared", R.raw.scared));
        bs.add(new ButtonSound(8, "Secret", R.raw.secret));
        bs.add(new ButtonSound(9, "Startled", R.raw.startled));
        bs.add(new ButtonSound(10, "Tired", R.raw.tired));
        bs.add(new ButtonSound(11, "Yes", R.raw.yes));
        return bs;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}