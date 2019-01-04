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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

/**
 *
 * @author Mathieu
 */
public class Guide implements Panel{
    
    private Scene scene;
    private SonGraph son;
    
    public Guide(DistributeurPanel dp){
        Group groupe=new Group();
        //scene=new Scene(groupe,800,600,Color.rgb(225,225,255));
        
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
        
        son=new SonGraph();
        son.setTranslateX(750);
        son.setTranslateY(20);
        
        
        Label direction=new Label(" - Pour déplacer les cases du taquin, on utilise les touches Z,S,Q,D.");
        direction.setFont(fs);
        direction.setTranslateX(20);
        
        Label imageSave=new Label();
        imageSave.setBackground(new Background(new BackgroundImage(new Image(source+"sauvegarde.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        imageSave.setBorder(new Border(new BorderStroke(Color.rgb(235,235,235), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        imageSave.setTranslateX(500);
        imageSave.setTranslateY(150);
        imageSave.setMinWidth(197);
        imageSave.setMinHeight(80);
        
        Label imageSavePop=new Label();
        imageSavePop.setBackground(new Background(new BackgroundImage(new Image(source+"sauvegardePop.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        imageSavePop.setBorder(new Border(new BorderStroke(Color.rgb(225,225,225), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        imageSavePop.setTranslateX(465);
        imageSavePop.setTranslateY(250);
        imageSavePop.setMinWidth(337);
        imageSavePop.setMinHeight(153);
        
        Label sauvegarde=new Label(" - Pour sauvegarder, appuyer sur Sauvegarder en cours de jeu.\n   Entrer un nom de sauvegarde "
                + "dans le champs indiqué.\n   Attention un nom déjà existant écrasera la sauvegarde !");
        sauvegarde.setFont(fs);
        sauvegarde.setTranslateX(20);
        sauvegarde.setTranslateY(50);
        
        Label aide=new Label("");
        aide.setFont(fs);
        
        vb.getChildren().add(menu);
        vb.getChildren().add(direction);
        vb.getChildren().add(sauvegarde);
        vb.getChildren().add(aide);
        groupe.getChildren().add(imageSave);
        groupe.getChildren().add(imageSavePop);
        groupe.getChildren().add(vb);
        groupe.getChildren().add(son);
        
    }
    
    @Override
    public Scene getScene() {
        
        son.afficheSon();
        return scene;
    }
    
}
