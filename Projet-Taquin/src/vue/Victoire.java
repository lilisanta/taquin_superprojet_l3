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
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
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
 * Panel de victoire
 * @author Mathieu
 */
class Victoire implements Panel{

    private Scene scene;
    private ScaleTransition st;
    private Group groupe;
    private String source;
    
    /**
     * Constructeur du panel de victoire
     * @param distri permet passer d'un panel à un autre
     */
    public Victoire(DistributeurPanel distri, String image){
        groupe=new Group();
        source = "file:///" + System.getProperty("user.dir") + "\\media\\images\\";
        source = source.replace('\\', '/');

        
        scene=new Scene(groupe,800,600,new ImagePattern(new Image(source+"fond.png")));
        
        afficheImage(image);
        
        DropShadow ds=new DropShadow();
        ds.setBlurType(BlurType.ONE_PASS_BOX);
        ds.setColor(Color.BLACK);
        
        Label victoire=new Label("Victoire");
        victoire.setMinWidth(100);
        victoire.setMinHeight(100);
        victoire.setTranslateY(250);
        victoire.setTranslateX(325);
        victoire.setFont(new Font("papyrus",48));
        victoire.setAlignment(Pos.CENTER);
        victoire.setTextFill(Color.rgb(235,235,235));
        victoire.setEffect(ds);
        
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
    
    
    /**
     * méthode hérité de Panel
     * lance l'animation à chaque appel
     * @return la scene graphique
     */
    @Override
    public Scene getScene() {
        st.jumpTo(Duration.ZERO);
        st.play();
        return scene;
    }

    /**
     * affiche l'image que le joueur à reconstituer précédemment
     * @param image nom de l'image à afficher
     */
    private void afficheImage(String image) {
        Label fond=new Label();
        fond.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source+image)), CornerRadii.EMPTY, Insets.EMPTY)));
        fond.setMinWidth(420);
        fond.setMinHeight(420);
        fond.setTranslateX(190);
        fond.setTranslateY(90);
        groupe.getChildren().add(0,fond);
    }
    
}
