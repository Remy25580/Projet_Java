package school.coda.remy_axel_ethan.projet_java.tools;

import javafx.scene.media.AudioClip;

import java.net.URL;

public class SoundManager {
    private static final String SOUNDS_LOCATION = "/school/coda/remy_axel_ethan/projet_java/sounds/";
    private static final AudioClip SHOOT = loadAudioClip(SOUNDS_LOCATION + "kaboom.wav");
    private static final AudioClip FAIL = loadAudioClip(SOUNDS_LOCATION + "the-funny-splash.wav");
    private static final AudioClip SHIP_SUNK_PLAYER = loadAudioClip(SOUNDS_LOCATION + "mimimi-clash-royale.wav");
    private static final AudioClip SHIP_SUNK_IA = loadAudioClip(SOUNDS_LOCATION + "Explosion.wav");
    private static final AudioClip VICTORY = loadAudioClip(SOUNDS_LOCATION + "victory.wav");
    private static final AudioClip DING_DING = loadAudioClip(SOUNDS_LOCATION + "dingding.wav");

    public static void playSunkPlayer() {
        if (SHIP_SUNK_PLAYER != null) SHIP_SUNK_PLAYER.play();
    }

    public static void playSunkIA() {
        if (SHIP_SUNK_IA != null) SHIP_SUNK_IA.play();
    }


    // Eviter de faire des return null...
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


