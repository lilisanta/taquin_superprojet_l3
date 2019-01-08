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
 * classe tampon entre la partie graphique et la partie modèle
 * gère aussi l'animation
 * @author Mathieu
 */
public class PlateauGraphique extends GridPane {

    private PlateauImage plateau;
    private int tailleCase;
    private int coups, time;

    /**
     * Constructeur du plateau graphique du panel GuiJeu lors de la création d'une partie
     * @param nbCase nombre de case d'un coté
     * @param image nom de l'image du casse tête
     */
    public PlateauGraphique(int nbCase, String image) {

        try {
            Plateau cons = GenerateurPlateau.genererPlateauConsole(nbCase);
            plateau = new PlateauImage(cons.getCases(), 0, 0, image);
            init();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    /**
     * Constructeur du plateau graphique du panel GuiJeu lors du chargement d'une partie sauvegardée
     * @param partie nom de la sauvegarde à charger
     */
    public PlateauGraphique(String partie) {
        try {
            plateau = PlateauImage.charger("media/plateau/" + partie);
            init();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * initialise les composants du plateau
     */
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

    /**
     * méthode tampon entre la partie graphique et la partie modèle du plateau
     * gère les deplacements
     * @param direction direction vers lequel doit bouger la case
     * @return retourne true si le plateau est reconstitué
     */
    public boolean deplacement(String direction) {
        switch (direction) {
            case "haut":
                animation(-1, 0);
                plateau.deplacement('h');
                break;
            case "gauche":
                animation(0, -1);
                plateau.deplacement('g');
                break;
            case "droite":
                animation(0, 1);
                plateau.deplacement('d');
                break;
            case "bas":
                animation(1, 0);
                plateau.deplacement('b');
                break;
        }
        return plateau.verifierVictoire();
    }

    /**
     * méthode tampon entre la partie graphique et la partie modèle
     * lance la sauvegarde au niveau du modèle
     * @param nomSauvegarde nom du fichier contenant la sauvegarde
     * @param coups le nombre de coups effectué avant la sauvegarde
     * @param time temps effectué au moment de la sauvegarde
     */
    public void sauvegarder(String nomSauvegarde, int coups, int time) {
        plateau.sauvegarder(nomSauvegarde,coups,time);
    }

    /**
     * gère la bonne animation des cases lors du déplacement
     * @param x abscisse à ajouter à l'abscisse de la case vide
     * @param y ordonnée à ajouter à l'ordonneé de la case vide
     */
    private void animation(int x, int y) {
        int posvx = 0, posvy = 0, posnx = 0, posny = 0;
        Case[][] cases = plateau.getCases();
        for (int i = 0; i < cases.length; i++) {
            for (int j = 0; j < cases[i].length; j++) {
                Case courante = cases[i][j];
                if (courante.estVide()) {
                    posvx = i;
                    posvy = j;
                }
            }

        }

        posnx = posvx + x;
        posny = posvy + y;

        
        
        Label result = new Label("Salut");
        for (Node node : this.getChildren()) {
            if (this.getRowIndex(node) == posnx && this.getColumnIndex(node) == posny) {


                result = (Label) node;
                // Première version qui marche
                /* Node tmp = result;
                this.getChildren().remove(result);
                this.add(tmp, posvy, posvx);*/

                moteurAnimation(this, result, x, y, posvx, posvy);

                break;

            }
        }

    }

    /**
     * animation du déplacement
     * @param gp this afin de supprimer l'ancienne case et d'ajouter la case aux coordonnées souhaités
     * @param result ancienne case
     * @param x direction abscisse du mouvement
     * @param y direction ordonnée du mouvement
     * @param posvx abscisse de la nouvelle case
     * @param posvy ordonnée de la nouvelle case
     */
    private void moteurAnimation(GridPane gp, Label result, int x, int y, int posvx, int posvy) {

        TranslateTransition tt = new TranslateTransition(Duration.millis(200), result);
        tt.setByX(-y * (420 / tailleCase));
        tt.setByY(-x * (420 / tailleCase));
        tt.play();
        tt.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label nouveau = new Label();
                nouveau.setAlignment(Pos.CENTER);
                nouveau.setText(result.getText());
                nouveau.setBackground(result.getBackground());
                nouveau.setMinWidth(420 / tailleCase);
                nouveau.setMinHeight(420 / tailleCase);
                gp.getChildren().remove(result);
                gp.add(nouveau, posvy, posvx);
            }
        });

    }


    /**
     * getter de coups
     * méthode tampon entre la partie graphique et la partie modèle
     * @return coups
     */
    public int getCoups() {
        return coups;
    }

    /**
     * getter de time
     * méthode tampon entre la partie graphique et la partie modèle
     * @return time
     */
    public int getTime() {
        return time;
    }

    /**
     * méthode tampon entre la partie graphique et la partie modèle
     * @return le nom de l'image du plateau
     */
    public String getImage() {
        return plateau.getImage();
    }

}
