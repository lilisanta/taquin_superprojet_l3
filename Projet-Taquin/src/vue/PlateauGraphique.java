/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import modele.*;
import taquinconsole.GenerateurPlateau;
import utils.ImageUtils;

/**
 *
 * @author Mathieu
 */
public class PlateauGraphique extends GridPane {

    private PlateauImage plateau;
    private int tailleCase;
    private int coups, time;

    public PlateauGraphique(int nbCase, String image) {

        try {
            Plateau cons = GenerateurPlateau.genererPlateauConsole(nbCase);
            plateau = new PlateauImage(cons.getCases(), 0, 0, image);
            init();
            System.out.println(plateau.toString());
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public PlateauGraphique(String partie) {
        try {
            plateau = PlateauImage.charger("media/plateau/" + partie);
            init();
            System.out.println(plateau.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void init() {
        this.setMinWidth(420);
        this.setMaxWidth(420);
        this.setMinHeight(420);
        coups = plateau.getNbcoups();
        time = plateau.getTemps();
        tailleCase = plateau.getCases().length;
        ImageUtils.split("media/images/" + plateau.getImage(), tailleCase);
        Case[][] cases = plateau.getCases();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                Case courante = cases[i][j];
                if (!courante.estVide()) {
                    int numCase = ((CaseNumerotee) courante).getNum();
                    Label l = new Label("" + numCase);
                    l.setMinWidth(420 / tailleCase);
                    l.setMinHeight(420 / tailleCase);
                    l.setAlignment(Pos.CENTER);
                    String source = "file:///" + System.getProperty("user.dir") + "∖∖media∖∖images∖∖tmp∖∖" + numCase + ".png";
                    source = source.replace("∖∖", "/");
                    l.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source)), CornerRadii.EMPTY, Insets.EMPTY)));
                    this.add(l, j, i);
                }
            }
        }
    }

    public boolean deplacement(String direction) {
        switch (direction) {
            case "haut":
                animation(-1, 0);
                plateau.deplacement('h');
                getCaseVide();
                System.out.println("deplacement haut");
                break;
            case "gauche":
                animation(0, -1);
                plateau.deplacement('g');
                getCaseVide();
                System.out.println("deplacement gauche");
                break;
            case "droite":
                animation(0, 1);
                plateau.deplacement('d');
                getCaseVide();
                System.out.println("deplacement droite");
                break;
            case "bas":
                animation(1, 0);
                plateau.deplacement('b');
                getCaseVide();
                System.out.println("deplacement bas");
                break;
        }
        System.out.println(plateau.toString());
        return plateau.verifierVictoire();
    }

    public void sauvegarder(String nomSauvegarde, int coups, int time) {
        plateau.sauvegarder(nomSauvegarde);
    }

    private void animation(int x, int y) {
        int posvx = 0, posvy = 0, posnx = 0, posny = 0;
        Case[][] cases = plateau.getCases();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                Case courante = cases[i][j];
                if (courante instanceof CaseVide) {
                    posvx = i;
                    posvy = j;
                }
            }

        }

        posnx = posvx + x;
        posny = posvy + y;

        System.out.println("animation(" + x + "," + y + ") | posvx=" + posvx + " - posvy=" + posvy + " | posnx=" + posnx + " - posvy=" + posny);

        Label result = new Label("Salut");
        for (Node node : this.getChildren()) {
            if (this.getRowIndex(node) == posnx && this.getColumnIndex(node) == posny) {

                System.out.println("Node(" + posnx + "," + posny + ") - " + node.toString());

                result = (Label) node;
                // Première version qui marche
                /* Node tmp = result;
                this.getChildren().remove(result);
                this.add(tmp, posvy, posvx);
                System.out.println(tmp.toString() + " | x=" + posvx + " | y=" + posvy);*/

                moteurAnimation(this, result, x, y, posvx, posvy);

                break;

            }
        }

    }

    private void moteurAnimation(GridPane gp, Label result, int x, int y, int posvx, int posvy) {

        TranslateTransition tt = new TranslateTransition(Duration.millis(200), result);
        tt.setByX(-y * (420 / tailleCase));
        tt.setByY(-x * (420 / tailleCase));
        tt.play();
        tt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //gp.getChildren().remove(result);
                //gp.add(tmp, posvy, posvx);
                Label coucou = new Label("Coucou");
                coucou.setAlignment(Pos.CENTER);
                coucou.setText(result.getText());
                coucou.setBackground(result.getBackground());
                coucou.setMinWidth(420 / tailleCase);
                coucou.setMinHeight(420 / tailleCase);
                gp.getChildren().remove(result);
                gp.add(coucou, posvy, posvx);
                //System.out.println("Tmp ("+posvx+","+posvy+")");
            }
        });

    }

    private void getCaseVide() {
        Case[][] cases = plateau.getCases();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                Case courante = cases[i][j];
                if (courante instanceof CaseVide) {
                    System.out.println("Case vide est en position (" + i + "," + j + ")");
                }
            }
        }

        //System.out.println("*********************************************");
    }

    public int getCoups() {
        return coups;
    }

    public int getTime() {
        return time;
    }

    public String getImage() {
        return plateau.getImage();
    }

}
