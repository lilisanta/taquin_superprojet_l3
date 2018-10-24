/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

/**
 *
 * @author Mathieu
 */
public class GuiJeu implements Panel{
    
    PlateauGraphique plato;
    Scene scene;
    int nbcoup=0;
    Label nbCoups;
    int time=0;
    Label temps;
    
    public GuiJeu(DistributeurPanel distri, PlateauGraphique pg){
        Group group=new Group();
        scene=new Scene(group,800,600, Color.rgb(244, 219, 106, 1));
        
        plato=pg;
        
        
        HBox hb=new HBox();
        VBox vb=new VBox();
        
      
        
        
        Label titre=new Label("GemTaquin");
        titre.setMinHeight(75);
        titre.setMinWidth(scene.getWidth()/3);
        titre.setStyle("-fx-background-color:rgb(67,247,47);-fx-background-radius:0px 0px 20px 0px;-fx-padding:20px");
        titre.setFont(new Font(25));
        titre.setTextFill(Color.rgb(27,27,155));
        titre.setAlignment(Pos.CENTER);
        
        
        Label menu=new Label("Menu");
        menu.setMinWidth(scene.getWidth()*2/12);
        Font fs=Font.font("Old English Text MT",FontPosture.REGULAR,30);
        menu.setFont(fs);
        menu.setTextFill(Color.rgb(27,27,235));
        menu.setStyle("-fx-padding:20px 0px 0px 0px;");
        
        
        menu.setAlignment(Pos.CENTER);
        
        Label typePartie=new Label("Solo");
        typePartie.setFont(fs);
        typePartie.setTextFill(Color.rgb(17,77,255));
        typePartie.setStyle("-fx-padding:20px 0px 0px 0px;");
        
        
        Label save=new Label("Sauvegarder");
        save.setFont(fs);
        save.setTextFill(Color.rgb(17,177,255));
        save.setStyle("-fx-padding: 20px 0px 0px 0px;");
        save.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                plato.sauvegarder();
            }
        
        });
        
        hb.getChildren().add(titre);
        hb.getChildren().add(menu);
        hb.getChildren().add(typePartie);
        hb.getChildren().add(save);
        hb.setMinHeight(90);
       
        Separator s=new Separator();
        s.setOrientation(Orientation.HORIZONTAL);
        s.setMinWidth(scene.getWidth()+100);
        s.setEffect(new DropShadow(10,0,5,Color.BLACK));
        
        
        
        
        HBox info=new HBox();
        info.setTranslateY(35);
        info.setAlignment(Pos.CENTER);
        info.setSpacing(100);
        
        nbCoups=new Label();
        nbCoups.setText("coups joués : "+getNbCoup());
        nbCoups.setFont(fs);
        
        
        temps=new Label();
        temps.setText("temps : "+getTime());
        temps.setFont(fs);
        
        Button aide=new Button("Aide");
        aide.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
            }
            
        });
        aide.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                
            }
            
        });
        aide.setFont(fs);
        aide.setStyle("background-image: url(\"../../../Ruche/public_html/images/image321.gif\");");
                
        info.getChildren().add(nbCoups);
        info.getChildren().add(temps);
        info.getChildren().add(aide);
        
        vb.getChildren().add(hb);
        vb.getChildren().add(s);
        vb.getChildren().add(plato);
        vb.getChildren().add(info);
        
        
        
        
        
        
        group.getChildren().add(vb);
    }
    
    
    
    
    
    
   
    /*
        
    */
    public Scene getScene(){
        return scene;
    }
    
    private int getNbCoup(){
        return nbcoup;
    }
    
    private void setNbCoup(){
        nbcoup++;
        nbCoups.setText("coups joués : "+nbcoup);
    }

    private int getTime() {
        
        return time;
    }
    
    /*
        
    */
    private void setTime(){
        time++;
        temps.setText("temps : "+time);
    }
    
}
