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
 *
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

    public GuiJeu(DistributeurPanel distri, PlateauGraphique pg, String type) {
        Group group = new Group();
        //scene=new Scene(group,800,600, Color.rgb(244, 219, 106, 1));
        String image = System.getProperty("user.dir") + "\\media\\images\\";
        image = image.replace('\\', '/');

        String user = "file:///" + image + "fond.png";
        String source = user.replace('\\', '/');
        scene = new Scene(group, 800, 600, new ImagePattern(new Image(source)));

        HBox hb = new HBox();
        VBox vb = new VBox();

        Font fs = Font.font("Papyrus", FontPosture.REGULAR, 30);

        Label menu = new Label("Menu");
        menu.setFont(fs);
        menu.setTextFill(Color.rgb(27, 27, 235));
        menu.setOnMouseClicked(new EventHandler<MouseEvent>() {

            Object[] options = {"OK", "CANCEL"};

            public void handle(MouseEvent event) {
                timeline.stop();
                JOptionPane jop = new JOptionPane();
                //jop.setComponentZOrder(null, 100);
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

        plato = pg;
        plato.setTranslateX(190);
        plato.setTranslateY(15);
        nbcoup = plato.getCoups();
        time = plato.getTime();

        scene.setOnKeyPressed(new EventHandler() {
            @Override
            public void handle(Event event) {
                //System.out.println(event.getSource());
                KeyEvent ke = (KeyEvent) event;
                //System.out.println(ke.getCharacter());
                System.out.println("*******************************************************");
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
                        System.out.println("touche non utile");
                        break;

                }
                System.out.println("fini=" + fini);
                if (fini) {
                    timeline.stop();
                    System.out.println("Victoire");
                    enregistrementBDD();
                    distri.finJeu(plato.getImage());
                    distri.changePanel("Victoire");
                }

                System.out.println("********************************\n\n\n");
            }

        });

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

        /*Button aide=new Button("Aide");
        aide.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
            }
            
        });
        aide.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
            }
            
        });
        aide.setFont(Font.font("papyrus"));*/
        info.getChildren().add(nbCoups);
        info.getChildren().add(temps);
        //info.getChildren().add(aide);

        vb.getChildren().add(hb);
        vb.getChildren().add(s);
        vb.getChildren().add(plato);
        vb.getChildren().add(info);

        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("temps=" + timeToHMS(time));
                        temps.setText("temps : " + timeToHMS(time++));
                    }
                })
        );
        timeline.setAutoReverse(false);
        timeline.setCycleCount(Timeline.INDEFINITE);

        group.getChildren().add(vb);
        //scene.getStylesheets().add("GuiCss.css");

    }

    private void sauvegarder(String nomSauvegarde) {
        plato.sauvegarder(nomSauvegarde,nbcoup,time);
    }

    public Scene getScene() {
        son.afficheSon();
        timeline.play();
        return scene;
    }

    private int getNbCoup() {
        return nbcoup;
    }

    private void setNbCoup() {
        nbcoup++;
        nbCoups.setText("coups joués : " + nbcoup);
    }

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
