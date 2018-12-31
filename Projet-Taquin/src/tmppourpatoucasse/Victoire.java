/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmppourpatoucasse;


import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author Mathieu
 */
class Victoire implements Panel{

    private Scene scene;
    private ScaleTransition st;
    
    public Victoire(DistributeurPanel distri){
        Group groupe=new Group();
        String source = "file:///" + System.getProperty("user.dir") + "\\media\\images\\";//sable.jpg";
        source = source.replace('\\', '/');

        
        scene=new Scene(groupe,800,600,new ImagePattern(new Image(source+"fond.png")));
        
        
        
        
        Label victoire=new Label("Victoire");
        victoire.setMinWidth(100);
        victoire.setMinHeight(100);
        victoire.setTranslateY(250);
        victoire.setTranslateX(325);
        victoire.setFont(new Font("papyrus",48));
        victoire.setAlignment(Pos.CENTER);
        
        st = new ScaleTransition(Duration.millis(4000), victoire);
        st.setToX(3);
        st.setToY(3);
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                distri.changePanel("menu");
            }
            
        });
        
        groupe.getChildren().add(victoire);
    }
    
    @Override
    public Scene getScene() {
        st.play();
        return scene;
    }
    
}
