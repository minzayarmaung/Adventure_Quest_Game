package Main;

import javax.sound.sampled.*;
import java.net.URL;

public class Music {

    Clip clip;

    public void setFile(URL name) {
        if (name == null) {
            System.err.println("Audio URL is null!");
            return;
        }

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(name);
            AudioFormat baseFormat = ais.getFormat();

            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );

            AudioInputStream pcmAis = AudioSystem.getAudioInputStream(decodedFormat, ais);

            clip = AudioSystem.getClip();
            clip.open(pcmAis);

        } catch (Exception e) {
            System.err.println("Error loading audio file: " + name);
            e.printStackTrace();
        }
    }

    public void play(URL name) {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loop(URL name) {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop(URL name) {
        if (clip != null) {
            clip.stop();
        }
    }
}