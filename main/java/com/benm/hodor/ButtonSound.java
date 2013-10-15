package com.benm.hodor;

import android.media.MediaPlayer;

/**
 * Created by Ben on 10/13/13.
 */
public class ButtonSound {

    public ButtonSound(){

    }
    public ButtonSound(int id, String name, int soundId){
        Name = name;
        Id = id;
        SoundId = soundId;
    }

    public String Name;
    public int SoundId;
    public int Id;
    public MediaPlayer Player;
}
