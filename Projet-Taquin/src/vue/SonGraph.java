/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;

/**
 * classe permettant la gestion de la musique graphiquement
 * @author Mathieu
 */
public class SonGraph extends Label{
    
    private Background sikON;
    private Background sikOFF;
    
    /**
     * Constructeur de l'icone de gestion de musique
     */
    public SonGraph(){
        setMinHeight(30);
        setMinWidth(30);
        String image=System.getProperty("user.dir")+"\\media\\images\\";
        image=image.replace('\\','/');
        String sonIm="file:///"+image+"";
        sikON=new Background(new BackgroundFill(new ImagePattern(new Image(sonIm+"son_on.jpg")), new CornerRadii(25, true), Insets.EMPTY));
        sikOFF=new Background(new BackgroundFill(new ImagePattern(new Image(sonIm+"son_off.png")), new CornerRadii(25, true), Insets.EMPTY));
        setBackground(sikON);
        setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(Musik.son){
                    Musik.interupteur();
                    setBackground(sikOFF);
                }else{
                    Musik.interupteur();
                    setBackground(sikON);
                }
            }
            
        });
    }
    
    /**
     * affiche la bonne icône suivant si la musique est joué ou pas
     */
    public void afficheSon(){
        if(Musik.son) setBackground(sikON);
        else setBackground(sikOFF);
    }
    
}
