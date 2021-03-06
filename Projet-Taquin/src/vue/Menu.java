/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

/**
 * classe d'accueil menant au différentes parties de l'application
 * @author Mathieu
 */
public class Menu implements Panel{
    
    private Scene scene;
    private SonGraph son;
    
    /**
     * Constructeur de Panel servant de menu d'accueil
     * @param distri permet de passer d'un panel à un autre
     */
    public Menu(DistributeurPanel distri){
        Group group=new Group();
        String source="file:///"+System.getProperty("user.dir")+"\\media\\images\\";
        source=source.replace('\\', '/');
        scene=new Scene(group,800,600, new ImagePattern(new Image(source+"fond.png")));
        
        
        
        Font fs=Font.font("Papyrus",FontPosture.REGULAR,40);
        
        VBox vb=new VBox();
        vb.minWidth(scene.getWidth());
        vb.minHeight(scene.getHeight());
        vb.setSpacing(5);
        
        Label titre=new Label("GEMTAQUIN");
        titre.setMinHeight(75);
        titre.setMinWidth(scene.getWidth()+9);
        titre.setFont(new Font(45));
        titre.setTextFill(Color.rgb(123,127,125));
        titre.setAlignment(Pos.CENTER);
        
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(4);
        innerShadow.setOffsetY(4);
        innerShadow.setColor(Color.rgb(34,30,30));
 
        titre.setEffect(innerShadow);
 
        vb.getChildren().add(titre);
        
        Label solo=new Label("Solo");
        solo.setAlignment(Pos.CENTER);
        solo.setFont(fs);
        solo.setTextFill(Color.rgb(17,77,255));
        solo.setMinWidth(scene.getWidth());
        solo.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                distri.changePanel("solo");
            }
            
        });
        vb.getChildren().add(solo);
        
        Label coop=new Label("Guide");
        coop.setAlignment(Pos.CENTER);
        coop.setFont(fs);
        coop.setTextFill(Color.rgb(17, 77, 255));
        coop.setMinWidth(scene.getWidth());
        coop.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                distri.changePanel("guide");
            }
            
        });
        vb.getChildren().add(coop);
        
        Label comp=new Label("Classement");
        comp.setAlignment(Pos.CENTER);
        comp.setFont(fs);
        comp.setTextFill(Color.rgb(17, 77, 255));
        comp.setMinWidth(scene.getWidth());
        comp.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                distri.changePanel("classement");
            }
            
        });
        comp.setStyle("-fx-padding: 40px;");
        
        vb.getChildren().add(comp);
        
        Label quitter=new Label("Quitter");
        quitter.setAlignment(Pos.CENTER);
        quitter.setFont(fs);
        quitter.setTextFill(Color.rgb(17, 77, 255));
        quitter.setMinWidth(scene.getWidth());
        quitter.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
            
        });
        quitter.setStyle("-fx-padding: 40px 0px 0px 0px;");
        
        Label copyright=new Label("Projet L3 Sciences Cognitives : Guillaume TOUSSAINT - Elisa SANTARELLI - Mathieu LECLAIRE");
        copyright.setAlignment(Pos.CENTER);
        copyright.setMinWidth(scene.getWidth());
        
        son=new SonGraph();
        son.setTranslateX(750);
        
        vb.getChildren().add(quitter);
        vb.getChildren().add(son);
        vb.getChildren().add(copyright);
        
        
        group.getChildren().add(vb);
    }
    
    /**
     * méthode hérité de Panel
     * @return la scene graphique
     */
    public Scene getScene(){
        son.afficheSon();
        return scene;
    }
    
}
