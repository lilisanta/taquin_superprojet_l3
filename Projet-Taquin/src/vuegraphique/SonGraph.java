/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vuegraphique;

import testgui.*;
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
 *
 * @author Mathieu
 */
public class SonGraph extends Label{
    
    public SonGraph(){
        setMinHeight(30);
        setMinWidth(30);
        String image=System.getProperty("user.dir")+"\\media\\images\\";
        image=image.replace('\\','/');
        String sonIm="file:///"+image+"";
        Background sikON=new Background(new BackgroundFill(new ImagePattern(new Image(sonIm+"son_on.jpg")), new CornerRadii(25, true), Insets.EMPTY));
        Background sikOFF=new Background(new BackgroundFill(new ImagePattern(new Image(sonIm+"son_off.png")), new CornerRadii(25, true), Insets.EMPTY));
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
    
}
