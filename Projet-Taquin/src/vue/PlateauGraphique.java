/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;


import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import taquinconsole.Case;
import taquinconsole.CaseNumerotee;
import taquinconsole.GenerateurPlateau;
import taquinconsole.Plateau;
import utils.ImageUtils;

/**
 *
 * @author Mathieu
 */
public class PlateauGraphique extends GridPane{
    
    
    private Plateau plateau;
    
    public PlateauGraphique(){
        this.setMinWidth(420);
        this.setMinHeight(420);
        try {
            plateau=GenerateurPlateau.chargerPlateau("taquin1");
            ImageUtils.split("crash_test_taquin.jpg",3);
            Case [][] cases = plateau.getCases();
            int compte = 1; 
            for (int i =0 ; i <cases.length ; i++) {
                for (int j=0 ; j<cases[i].length ; j++){
                    Case courante = cases [i][j];
                    if(courante instanceof CaseNumerotee) {
                        Label l = new Label(""+((CaseNumerotee) courante).getNum()); 
                      // l.setStyle("-fx-background-image: url('media/images/tmp/"+compte+".png∖');");
                      l.setMinWidth(420/3);
                      l.setMinHeight(420/3);
                      l.setAlignment(Pos.CENTER);
                      String source = "file:///"+System.getProperty("user.dir")+"∖∖media∖∖images∖∖tmp∖∖"+compte+".png";
                      source=source.replace("∖∖", "/");
                      l.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source)),CornerRadii.EMPTY,Insets.EMPTY)) );  
                      this.add(l,i,j);
                        compte++;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
           
        }
    }
    
    
    public void sauvegarder(){
        plateau.sauvegarder();
    }
    
}
