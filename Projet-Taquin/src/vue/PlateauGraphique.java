/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;


import java.io.IOException;
import javafx.scene.layout.GridPane;
import taquinconsole.GenerateurPlateau;
import taquinconsole.Plateau;

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
            plateau=GenerateurPlateau.chargerPlateau("C:\\Users\\Mathieu\\Documents\\NetBeansProjects\\taquin_superprojet_l3\\Projet-Taquin\\media\\plato\\taquin1");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void sauvegarder(){
        plateau.sauvegarder();
    }
    
}
