/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;



import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.Event;
import testgui.plateau.*;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javax.swing.JOptionPane;
import utils.DBHelper;

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
    
    public GuiJeu(DistributeurPanel distri, PlateauGraphique pg, String type){
        Group group=new Group();
        //scene=new Scene(group,800,600, Color.rgb(244, 219, 106, 1));
        String image=System.getProperty("user.dir")+"\\media\\images\\";
        image=image.replace('\\','/');
        
        String user="file:///"+image+"fond.png";
        String source=user.replace('\\', '/');
        scene=new Scene(group,800,600, new ImagePattern(new Image(source)));
      
        
        HBox hb=new HBox();
        VBox vb=new VBox();
        
      
        
        Font fs=Font.font("Papyrus",FontPosture.REGULAR,30);
        
        
        
        /*Label titre=new Label("GemTaquin");
        titre.setMinHeight(75);
        titre.setMinWidth(scene.getWidth()/3);
        titre.setStyle("-fx-border-image: linear-gradient(red,blue) 35px;"
                + "-fx-padding:20px");
        titre.setBorder(new Border(new BorderImage(new Image(source+"hieroglyphe.jpg"),BorderWidths.FULL,Insets.EMPTY,BorderWidths.EMPTY,true,BorderRepeat.REPEAT,BorderRepeat.REPEAT)));
        titre.setFont(fs);
        titre.setTextFill(Color.rgb(27,27,155));
        titre.setAlignment(Pos.CENTER);*/
        
        
        Label menu=new Label("Menu");
        menu.setFont(fs);
        menu.setTextFill(Color.rgb(27,27,235));
        menu.setOnMouseClicked(new EventHandler<MouseEvent>(){
            
           Object[] options = { "OK", "CANCEL" };
            public void handle(MouseEvent event) {
                JOptionPane jop=new JOptionPane();
                //jop.setComponentZOrder(null, 100);
                int res=jop.showOptionDialog(null, "Voulez-vous quitter la partie ?\nToute partie non-sauvegardée sera perdue !",
                        "Attention", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options, options[0]);
                if(res==JOptionPane.OK_OPTION){
                    distri.changePanel("menu");
                }
                
                //Alert alert=new Alert(AlertType.CONFIRMATION);
                
                
            }
            
        });
        
        Label typePartie=new Label(type);
        typePartie.setFont(fs);
        typePartie.setTextFill(Color.rgb(17,77,255));
        
        
        Label save=new Label("Sauvegarder");
        save.setFont(fs);
        save.setTextFill(Color.rgb(17,77,255));
        save.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                JOptionPane jop=new JOptionPane();
                String res=jop.showInputDialog(null,"Veillez entrer un nom pour la sauvegarde","sauvegarde");
                if(res.equals("")){
                    JOptionPane.showMessageDialog(null,"Il faut entrer un nom");
                }else{
                    sauvegarder(res);
                }
                
            }
        
        });
        
        SonGraph son=new SonGraph();
        son.setTranslateY(20);
        
        hb.getChildren().add(new Label());
        hb.getChildren().add(menu);
        hb.getChildren().add(typePartie);
        hb.getChildren().add(save);
        hb.getChildren().add(son);
        hb.setMinHeight(70);
        hb.setSpacing(95);
       
        Separator s=new Separator();
        s.setOrientation(Orientation.HORIZONTAL);
        s.setMinWidth(scene.getWidth()+100);
        s.setEffect(new DropShadow(10,0,5,Color.BLACK));
        
        
        plato=pg;
        plato.setTranslateX(190);
        plato.setTranslateY(15);
        nbcoup=plato.getCoups();
        time=plato.getTime();
        
        scene.setOnKeyPressed(new EventHandler(){
            @Override
            public void handle(Event event) {
                //System.out.println(event.getSource());
                KeyEvent ke=(KeyEvent) event;
                //System.out.println(ke.getCharacter());
                System.out.println("*******************************************************");
                boolean fini=false;
                switch(ke.getText()){
                    case "s":
                    case "S":
                        fini=plato.deplacement("haut");
                        setNbCoup();
                        break;
                    case "z":
                    case "Z":
                        fini=plato.deplacement("bas");
                        setNbCoup();
                        break;
                    case "q":
                    case "Q":
                        fini=plato.deplacement("droite");
                        setNbCoup();
                        break;
                    case "d":
                    case "D":
                        fini=plato.deplacement("gauche");
                        setNbCoup();
                        break;
                    default: 
                        System.out.println("touche non utile");
                        break;
                        
                }
                System.out.println("fini="+fini);
                if(fini){
                    System.out.println("Victoire");
                    enregistrementBDD();
                    distri.finJeu(plato.getImage());
                    distri.changePanel("Victoire");
                }
                
                    System.out.println("********************************\n\n\n");
            }
        
        
        });
        
        
        
        
        HBox info=new HBox();
        info.setTranslateX(200);
        info.setTranslateY(20);
        info.setSpacing(100);
        
        nbCoups=new Label();
        nbCoups.setText("coups joués : "+getNbCoup());
        nbCoups.setFont(fs);
        
        
        temps=new Label();
        temps.setText("temps : "+getTime());
        temps.setFont(fs);
        
        /*Button aide=new Button("Aide");
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
        aide.setFont(Font.font("papyrus"));*/
                
        info.getChildren().add(nbCoups);
        info.getChildren().add(temps);
        //info.getChildren().add(aide);
        
        vb.getChildren().add(hb);
        vb.getChildren().add(s);
        vb.getChildren().add(plato);
        vb.getChildren().add(info);
        
        
        
        
        
        
        group.getChildren().add(vb);
        //scene.getStylesheets().add("GuiCss.css");
        
        
    }
    
    
    
    
    private void sauvegarder(String nomSauvegarde){
        plato.sauvegarder(nomSauvegarde);
    }
    
   
    
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
    
    private void setTime(){
        
    }
    
    public void enregistrementBDD(){
        String temps=time+"";
        SimpleDateFormat formater = null;
        Date aujourdhui = new Date();
        formater = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        String date=formater.format(aujourdhui);
        JOptionPane jop=new JOptionPane();
        String res=jop.showInputDialog(null,"Veillez entrer un pseudo","sauvegarde");
        try{
        if(!res.equals("")){
             DBHelper.insertScores("solo", res, nbcoup, temps, date);
        }
        }catch(NullPointerException ex){
            
        }
    }
    
}
