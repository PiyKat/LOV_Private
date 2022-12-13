package src.java.modules;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that creates an AudioInputStream object for plating .wav files
 */
public class MusicDevice {

    Clip clip;

    AudioInputStream audioInputStream;
    static String filePath;

    // constructor to initialize streams and clip
    public MusicDevice (String filePath)
            throws javax.sound.sampled.UnsupportedAudioFileException,
            java.io.IOException, javax.sound.sampled.LineUnavailableException {
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    // Method to play the audio
    public void play() {
        //start the clip
        clip.start();
    }

}
