package soundEffects;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;

public class SoundEffects {
    private Clip[] moveSounds = new Clip[4];
    private Clip[] strikeSounds = new Clip[4];
    private Clip endSound;

    public SoundEffects() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Loading Move sounds
        File file = new File("sounds\\move1.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        moveSounds[0] = AudioSystem.getClip();
        moveSounds[0].open(audioStream);
        file = new File("sounds\\move2.wav");
        audioStream = AudioSystem.getAudioInputStream(file);
        moveSounds[1] = AudioSystem.getClip();
        moveSounds[1].open(audioStream);
        file = new File("sounds\\move3.wav");
        audioStream = AudioSystem.getAudioInputStream(file);
        moveSounds[2] = AudioSystem.getClip();
        moveSounds[2].open(audioStream);
        file = new File("sounds\\move4.wav");
        audioStream = AudioSystem.getAudioInputStream(file);
        moveSounds[3] = AudioSystem.getClip();
        moveSounds[3].open(audioStream);

        // Loading strikeSounds
        file = new File("sounds\\strike1.wav");
        audioStream = AudioSystem.getAudioInputStream(file);
        strikeSounds[0] = AudioSystem.getClip();
        strikeSounds[0].open(audioStream);
        file = new File("sounds\\strike2.wav");
        audioStream = AudioSystem.getAudioInputStream(file);
        strikeSounds[1] = AudioSystem.getClip();
        strikeSounds[1].open(audioStream);
        file = new File("sounds\\strike3.wav");
        audioStream = AudioSystem.getAudioInputStream(file);
        strikeSounds[2] = AudioSystem.getClip();
        strikeSounds[2].open(audioStream);
        file = new File("sounds\\strike4.wav");
        audioStream = AudioSystem.getAudioInputStream(file);
        strikeSounds[3] = AudioSystem.getClip();
        strikeSounds[3].open(audioStream);

        // end Sound
        file = new File("sounds\\victory.wav");
        audioStream = AudioSystem.getAudioInputStream(file);
        endSound = AudioSystem.getClip();
        endSound.open(audioStream);

        audioStream.close();
    }

    public void playMove() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        moveSounds[randomNumber].setMicrosecondPosition(0);
        moveSounds[randomNumber].start();
    }

    public void playStrike() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        strikeSounds[randomNumber].setMicrosecondPosition(0);
        strikeSounds[randomNumber].start();
    }

    public void playEnd(){
        endSound.setMicrosecondPosition(0);
        endSound.start();
    }

}