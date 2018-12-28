/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuegraphique;

import testgui.*;
import java.util.HashMap;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author Mathieu
 */
public class Musik {

    private static HashMap<String, Media> hm = new HashMap<String, Media>();
    private static MediaPlayer mediaplayer;
    public static boolean son=true;

    public static void initMusik() {

        String src = "file:///C:/Users/Mathieu/Documents/NetBeansProjects/taquin_superprojet_l3/Projet-Taquin/media/music/";
        String srcT = "file://media/music/";
        //String internetSrc="https://www.youtube.com/watch?v=rzsGK96V2cQ";

        String user = "file:///" + System.getProperty("user.dir") + "\\media\\music\\";
        String source = user.replace('\\', '/');

        Media musikJeu = new Media(source + "28.mp3");
        Media musikMenu = new Media(src + "testMedia.mp3");
        Media musikDefault = new Media(src + "28.mp3");

        hm.put("menu", musikMenu);
        hm.put("solo", musikMenu);
        hm.put("jeu", musikJeu);
        hm.put("default", musikDefault);

        mediaplayer = new MediaPlayer(musikDefault);
        mediaplayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                System.out.println("Fin de transmission");
                mediaplayer.stop();
                mediaplayer.setStartTime(Duration.ZERO);
                mediaplayer.play();
            }
        });
        mediaplayer.setOnReady(new Runnable() {
            public void run() {
                mediaplayer.setStartTime(Duration.ZERO);
                mediaplayer.setStopTime(mediaplayer.getMedia().getDuration());
            }
        });
    }

    public static void lancerMusic() {
        mediaplayer.play();
    }

    public static void couperMusic() {
        mediaplayer.stop();
    }

    public static void choisirMusik(String zik) {
        mediaplayer = new MediaPlayer(hm.get(zik));
    }

    public static boolean estMedia(String zik) {
        boolean res = mediaplayer.getMedia().equals(hm.get(zik));
        return res;
    }

    public static void changeMusik(String src) {
        if (son) {
            switch (src) {
                case "menu":
                case "solo":
                case "jeu":
                    if (!estMedia(src)) {
                        couperMusic();
                        choisirMusik(src);
                        lancerMusic();
                    }
                    break;

                default:
                    Musik.choisirMusik("default");
            }
        }
    }

    public static void interupteur() {
        son = !son;
        if (son) {
            Musik.lancerMusic();
        } else {
            Musik.couperMusic();
        }
    }

}
