package UI.Audio;

import javax.sound.sampled.*;
import java.io.File;

public class Audio {

    private Clip clip;

    public Audio(String file) {
        try {
            File audioPath = new File("./assets/Audio/" + file + ".wav");

            if (audioPath.exists()) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(audioPath);
                clip = AudioSystem.getClip();
                clip.open(audio);
            } else {
                System.err.println("Invalid file path");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Start(boolean isLooping){
        clip.start();
        if(isLooping){
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void Close(){
        clip.stop();
    }

    /**
     * Changes volume of the music according to volumePercent.
     * 0.0: Muted - 1.0: 100% Volume
     * @param volumePercent volume percentage
     */
    public void SetVolume(double volumePercent){
        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(volumePercent) / Math.log(10.0) * 20.0);
        volume.setValue(dB);
    }
}
