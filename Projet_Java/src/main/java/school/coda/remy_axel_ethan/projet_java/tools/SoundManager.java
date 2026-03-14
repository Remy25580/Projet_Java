package school.coda.remy_axel_ethan.projet_java.tools;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class SoundManager {

    private static final AudioClip SHOOT = loadAudioClip("/school/coda/remy_axel_ethan/projet_java/sounds/kaboom.wav");
    private static final AudioClip FAIL = loadAudioClip("/school/coda/remy_axel_ethan/projet_java/sounds/the-funny-splash.wav");
    private static final AudioClip SHIP_SUNK_PLAYER = loadAudioClip("/school/coda/remy_axel_ethan/projet_java/sounds/mimimi-clash-royale.wav");
    private static final AudioClip SHIP_SUNK_IA = loadAudioClip("/school/coda/remy_axel_ethan/projet_java/sounds/Explosion.wav");
    private static final AudioClip VICTORY = loadAudioClip("/school/coda/remy_axel_ethan/projet_java/sounds/victory.wav");
    private static final AudioClip DING_DING = loadAudioClip("/school/coda/remy_axel_ethan/projet_java/sounds/dingding.wav");

    public static void playSunkPlayer() {
        if (SHIP_SUNK_PLAYER != null) SHIP_SUNK_PLAYER.play();
    }

    public static void playSunkIA() {
        if (SHIP_SUNK_IA != null) SHIP_SUNK_IA.play();
    }


    private static AudioClip loadAudioClip(String path) {
        try {
            URL resource = SoundManager.class.getResource(path);
            if (resource == null) {
                System.err.println("ERREUR : Bruitage introuvable : " + path);
                return null;
            }
            return new AudioClip(resource.toExternalForm());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void playBackgroundMusic(String path) {
        MediaPlayer musicPlayer;
        try {
            URL resource = SoundManager.class.getResource(path);
            if (resource == null) {
                System.err.println("ERREUR : Musique introuvable : " + path);
                return;
            }

            Media media = new Media(resource.toExternalForm());
            musicPlayer = new MediaPlayer(media);

            musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            musicPlayer.setVolume(0.3);
            musicPlayer.play();

            System.out.println("SUCCÈS : Musique lancée : " + path);
        } catch (Exception e) {
            System.err.println("ERREUR lors de la lecture de la musique.");
            e.printStackTrace();
        }
    }

    public static void playExplosion() {
        if (SHOOT != null) {
            SHOOT.setVolume(100.0);
            SHOOT.play();
        }
    }

    public static void playFail() {
        if (FAIL != null) {
            FAIL.setVolume(1.0);
            FAIL.play();
        }
    }

    public static void playVictory() {
        if (VICTORY != null) {
            VICTORY.setVolume(10.0);
            VICTORY.play();
        }
    }

    public static void playDingDing() {
        if (DING_DING != null) {
            DING_DING.setVolume(1.0);
            DING_DING.play();
        }
    }
}


