/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;


import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
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
    private Group groupe;
    private String source;
    
    public Victoire(DistributeurPanel distri){
        groupe=new Group();
        source = "file:///" + System.getProperty("user.dir") + "\\media\\images\\";//sable.jpg";
        source = source.replace('\\', '/');

        
        scene=new Scene(groupe,800,600,new ImagePattern(new Image(source+"fond.png")));
        
        
        
        
        Label victoire=new Label("Victoire");
        victoire.setMinWidth(100);
        victoire.setMinHeight(100);
        victoire.setTranslateY(250);
        victoire.setTranslateX(325);
        victoire.setFont(new Font("papyrus",48));
        victoire.setAlignment(Pos.CENTER);
        victoire.setTextFill(Color.rgb(235,235,235));
        
        st = new ScaleTransition(Duration.millis(4000), victoire);
        st.setToX(3);
        st.setToY(3);
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                distri.changePanel("menu");
            }
        });
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
        st.jumpTo(Duration.ZERO);
        st.play();
        return scene;
    }

    void afficheImage(String image) {
        Label fond=new Label();
        fond.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source+image)), CornerRadii.EMPTY, Insets.EMPTY)));
        fond.setMinWidth(420);
        fond.setMinHeight(420);
        fond.setTranslateX(190);
        fond.setTranslateY(90);
        groupe.getChildren().add(0,fond);
    }
    
}
