/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;


import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import vue.DistributeurPanel;
import vue.Panel;
import vue.SonGraph;

/**
 *
 * @author Mathieu
 */
public class Classement implements Panel{

    private Scene scene;
    
    public Classement(){
        Group groupe=new Group();
        scene=new Scene(groupe,800,600,Color.rgb(225,225,255));
        
    }

    Classement(DistributeurPanel dp) {
        Group groupe=new Group();
        String source = "file:///" + System.getProperty("user.dir") + "\\media\\images\\";//sable.jpg";
        source = source.replace('\\', '/');

        
        scene=new Scene(groupe,800,600,new ImagePattern(new Image(source+"fond.png")));
        
        Font fs = Font.font("Papyrus", FontPosture.REGULAR, 18);
        
        VBox vb=new VBox();
        vb.setSpacing(25);
        
        Label menu = new Label("Menu");
        menu.setFont(new Font("papyrus",30));
        menu.setTranslateX(40);
        menu.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                dp.changePanel("menu");
                System.out.println("Clicketi clicketa");
            }

        });
        
        SonGraph son=new SonGraph();
        son.setTranslateX(750);
        son.setTranslateY(20);
        
        
        
        vb.getChildren().add(menu);
        groupe.getChildren().add(vb);
        groupe.getChildren().add(son);
    }
    
    @Override
    public Scene getScene() {
        
        return scene;
    }
    
}
