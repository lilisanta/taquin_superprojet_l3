/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import utils.DBHelper;

/**
 * classe permettant de jouer au taquin
 * @author Mathieu
 */
public class GuiJeu implements Panel {

    private PlateauGraphique plato;
    private Scene scene;
    private int nbcoup = 0;
    private Label nbCoups;
    private int time = 0;
    private Label temps;
    private Timeline timeline;
    private SonGraph son;

    
    /**
     * Constructeur de la scene graphique du jeu
     * @param distri permet de passer d'un panel à un autre
     * @param pg le plateau graphique jouable
     * @param type le type de partie initialement prévu (Solo, coopération ou compétition)
     */
    public GuiJeu(DistributeurPanel distri, PlateauGraphique pg, String type) {
        Group group = new Group();
        String source = "file:///" + System.getProperty("user.dir") + "\\media\\images\\";
        source = source.replace('\\', '/');
        scene = new Scene(group, 800, 600, new ImagePattern(new Image(source+ "fond.png")));

        HBox hb = new HBox();
        VBox vb = new VBox();

        Font fs = Font.font("Papyrus", FontPosture.REGULAR, 30);

        
        // Barre de navigation
        Label menu = new Label("Menu");
        menu.setFont(fs);
        menu.setTextFill(Color.rgb(27, 27, 235));
        menu.setOnMouseClicked(new EventHandler<MouseEvent>() {

            Object[] options = {"OK", "CANCEL"};

            public void handle(MouseEvent event) {
                timeline.stop();
                JOptionPane jop = new JOptionPane();
                int res = jop.showOptionDialog(null, "Voulez-vous quitter la partie ?\nToute partie non-sauvegardée sera perdue !",
                        "Attention", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                if (res == JOptionPane.OK_OPTION) {
                    distri.changePanel("menu");
                }else{
                    timeline.play();
                }

            }

        });

        Label typePartie = new Label(type);
        typePartie.setFont(fs);
        typePartie.setTextFill(Color.rgb(17, 77, 255));

        Label save = new Label("Sauvegarder");
        save.setFont(fs);
        save.setTextFill(Color.rgb(17, 77, 255));
        save.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                timeline.stop();
                JOptionPane jop = new JOptionPane();
                String res = jop.showInputDialog(null, "Veillez entrer un nom pour la sauvegarde", "sauvegarde");
                try{
                    if (res.equals("")) {
                        JOptionPane.showMessageDialog(null, "Il faut entrer un nom");
                    } else {
                        sauvegarder(res);
                    }
                }catch(NullPointerException ex){
                    
                }
                timeline.play();
            }

        });

        son = new SonGraph();
        son.setTranslateY(20);

        hb.getChildren().add(new Label());
        hb.getChildren().add(menu);
        hb.getChildren().add(typePartie);
        hb.getChildren().add(save);
        hb.getChildren().add(son);
        hb.setMinHeight(70);
        hb.setSpacing(95);

        Separator s = new Separator();
        s.setOrientation(Orientation.HORIZONTAL);
        s.setMinWidth(scene.getWidth() + 100);
        s.setEffect(new DropShadow(10, 0, 5, Color.BLACK));

        
        // initialisation de la partie
        plato = pg;
        plato.setTranslateX(190);
        plato.setTranslateY(15);
        nbcoup = plato.getCoups();
        time = plato.getTime();

        
        // controleur 
        scene.setOnKeyPressed(new EventHandler() {
            @Override
            public void handle(Event event) {
                KeyEvent ke = (KeyEvent) event;
                boolean fini = false;
                switch (ke.getText()) {
                    case "s":
                    case "S":
                        fini = plato.deplacement("haut");
                        setNbCoup();
                        break;
                    case "z":
                    case "Z":
                        fini = plato.deplacement("bas");
                        setNbCoup();
                        break;
                    case "q":
                    case "Q":
                        fini = plato.deplacement("droite");
                        setNbCoup();
                        break;
                    case "d":
                    case "D":
                        fini = plato.deplacement("gauche");
                        setNbCoup();
                        break;
                    default:
                        break;

                }
                
                if (fini) {
                    timeline.stop();
                    enregistrementBDD();
                    distri.finJeu(plato.getImage());
                    distri.changePanel("Victoire");
                }

            }

        });

        // affichage des statistiques
        HBox info = new HBox();
        info.setTranslateX(200);
        info.setTranslateY(20);
        info.setSpacing(100);

        nbCoups = new Label();
        nbCoups.setText("coups joués : " + getNbCoup());
        nbCoups.setFont(fs);

        temps = new Label();
        temps.setText("temps : " + timeToHMS(time));
        temps.setFont(fs);

       
        info.getChildren().add(nbCoups);
        info.getChildren().add(temps);

        vb.getChildren().add(hb);
        vb.getChildren().add(s);
        vb.getChildren().add(plato);
        vb.getChildren().add(info);

        
        // Timer de la partie
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        temps.setText("temps : " + timeToHMS(time++));
                    }
                })
        );
        timeline.setAutoReverse(false);
        timeline.setCycleCount(Timeline.INDEFINITE);

        group.getChildren().add(vb);

    }

    
    /**
     * méthode pour lancer la sauvegarde du plateau
     * @param nomSauvegarde nom du fichier de sauvegarde
     */
    private void sauvegarder(String nomSauvegarde) {
        plato.sauvegarder(nomSauvegarde,nbcoup,time);
    }

    /**
     * méthode hérité de Panel
     * lance le compte du temps
     * @return la scene graphique
     */
    public Scene getScene() {
        son.afficheSon();
        timeline.play();
        return scene;
    }

    /**
     * getter du nombre de coups
     * @return nbcoups
     */
    private int getNbCoup() {
        return nbcoup;
    }

    
    /**
     * setter du nombre de coup
     * incremente le nombre de coups et l'affiche
     */
    private void setNbCoup() {
        nbcoup++;
        nbCoups.setText("coups joués : " + nbcoup);
    }

    
    /**
     * permet l'affichage du temps en heures, minutes et secondes
     * @param tempsS temps en secondes
     * @return le temps formaliser HH h MM min ss s
     */
    private String timeToHMS(int tempsS) {

        // IN : (long) temps en secondes
        // OUT : (String) temps au format texte : "1 h 26 min 3 s"
        int h = (int) (tempsS / 3600);
        int m = (int) ((tempsS % 3600) / 60);
        int s = (int) (tempsS % 60);
        String r = "";
        if (h > 0) {
            r += h + " h ";
        }
        if (m > 0) {
            r += m + " min ";
        }
        if (s > 0) {
            r += s + " s";
        }
        if (h <= 0 && m <= 0 && s <= 0) {
            r = "0 s";
        }

        return r;
    }
    
    /**
     * permet l'affichage du temps pour la base de données
     * @param tempsS temps en secondes
     * @return le temps formaliser HH:MM:ss
     */
    private String timeToBase(int tempsS) {

        // IN : (long) temps en secondes
        // OUT : (String) temps au format texte : "1 h 26 min 3 s"
        int h = (int) (tempsS / 3600);
        int m = (int) ((tempsS % 3600) / 60);
        int s = (int) (tempsS % 60);
        String r = "";
        if (h > 10) r += h + ":";
        else r+="0"+h+":";
        if (m > 10) r += m + ":";
        else r+="0"+m+":";
        if (s > 10) r += s + "";
        else r+="0"+s+"";
        if (h <= 0 && m <= 0 && s <= 0) r = "00:00:00";
            

        return r;
    }

    

    /**
     * méthode pour enregistrer un vainqueur dans la base avec un pseudo, un nombre de coups, le temps effectué et la date de victoire
     */
    public void enregistrementBDD() {
        String tempsParti = timeToBase(time);
        SimpleDateFormat formater = null;
        Date aujourdhui = new Date();
        formater = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        String date = formater.format(aujourdhui);
        JOptionPane jop = new JOptionPane();
        String res = jop.showInputDialog(null, "       Vous avez gagné ! \nVeillez entrer un pseudo pour le classement", "Victoire", JOptionPane.PLAIN_MESSAGE);
        try {
            if (!res.equals("")) {
                DBHelper.insertScores("solo", res, nbcoup, tempsParti, date);
            }
        } catch (NullPointerException ex) {

        }
    }

}
