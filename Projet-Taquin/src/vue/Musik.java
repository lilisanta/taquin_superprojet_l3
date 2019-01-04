/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.HashMap;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * classe permettant de jouer de la musique dans toute l'application
 * @author Mathieu
 */
public class Musik {

    private static HashMap<String, Media> hm = new HashMap<String, Media>();
    private static MediaPlayer mediaplayer;
    public static boolean son = true;

    /**
     * initialise la musiques, associe un panel à une musique et charge le mediaplayer
     */
    public static void initMusik() {

        String user = "file:///" + System.getProperty("user.dir") + "\\media\\music\\";
        String source = user.replace('\\', '/');

        Media musikJeu = new Media(source + "jeu.mp3");
        Media musikMenu = new Media(source + "menu.mp3");
        Media musikDefault = new Media(source + "jeu.mp3");
        Media musikVictoire = new Media(source + "victoire.mp3");

        hm.put("menu", musikMenu);
        hm.put("guide", musikMenu);
        hm.put("classement", musikMenu);
        hm.put("solo", musikMenu);
        hm.put("jeu", musikJeu);
        hm.put("Victoire", musikVictoire);
        hm.put("default", musikDefault);

        mediaplayer = new MediaPlayer(musikDefault);
        
        mediaplayer.setOnReady(new Runnable() {
            public void run() {
                mediaplayer.setStartTime(Duration.ZERO);
                mediaplayer.setStopTime(mediaplayer.getMedia().getDuration());
            }
        });
        mediaplayer.setCycleCount(AudioClip.INDEFINITE);
    }

    
    /**
     * méthode qui lance la musique
     */
    public static void lancerMusic() {
        mediaplayer.play();
    }

    
    /**
     * méthode qui coupe la musique
     */
    public static void couperMusic() {
        mediaplayer.stop();
    }

    
    /**
     * change la musique à joué
     * @param zik nom du media à changer
     */
    public static void choisirMusik(String zik) {
        mediaplayer = new MediaPlayer(hm.get(zik));
    }

    /**
     * 
     * Utile pour éviter de relancer la musique à chaque changement de panel qui ont la même
     * @param zik nom du media à changer
     * @return retourne true si la musique à changer est la même que celle du mediaplayer sinon retourne false 
     */
    public static boolean estMedia(String zik) {
        boolean res = mediaplayer.getMedia().equals(hm.get(zik));
        return res;
    }

    /**
     * permet de permutter les musiques
     * @param src nom du panel (et de la musique) à changer 
     */
    public static void changeMusik(String src) {

        switch (src) {
            case "menu":
            case "solo":
            case "Victoire":
            case "classement":
            case "guide":
            case "jeu":
                if (!estMedia(src)) {
                    couperMusic();
                    choisirMusik(src);
                    if (son) {
                        lancerMusic();

                    }
                }
                break;

            default:
                Musik.choisirMusik("default");
        }
    }

    /**
     * permet de couper et de lancer la musique à n'importe quel moment du jeu
     */
    public static void interupteur() {
        son = !son;
        if (son) {
            Musik.lancerMusic();
        } else {
            Musik.couperMusic();
        }
    }

}
