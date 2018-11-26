/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;


import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
            ImageUtils.split("crash_test_taquin.jpg",4);
            Case [][] cases = plateau.getCases();
            int compte = 1; 
            for (int i =0 ; i <cases.length ; i++) {
                for (int j=0 ; j<cases[i].length ; j++){
                    Case courante = cases [i][j];
                    if(courante instanceof CaseNumerotee) {
                        Label l = new Label(""+((CaseNumerotee) courante).getNum()); 
                       //l.setstyle("-fxbackground-image: url(∖"media/images/tmp/"+compte+".png∖");");
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
