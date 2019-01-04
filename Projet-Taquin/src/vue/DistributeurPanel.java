/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mathieu
 */
public class DistributeurPanel {
    
    private Stage fenetre;
    private HashMap listPanel;
    
    public DistributeurPanel(Stage frame){
        
        fenetre=frame;
        listPanel=new HashMap<String,Panel>();
        
        Menu menu=new Menu(this);
        MenuSolo solo=new MenuSolo(this);
        GuiJeu gui=new GuiJeu(this, new PlateauGraphique(3,"image-taquin1.jpg"),"Solo");
        Victoire vic=new Victoire(this);
        Guide guide=new Guide(this);
        Classement classement=new Classement(this);
        
        listPanel.put("menu", menu);
        listPanel.put("jeu", gui);
        listPanel.put("solo",solo);
        listPanel.put("Victoire", vic);
        listPanel.put("guide",guide);
        listPanel.put("classement",classement);
        
        Musik.initMusik();
    }
    
    public void nouveauJeu(PlateauGraphique pg, String type){
        GuiJeu gui=new GuiJeu(this,pg,type);
        listPanel.put("jeu",gui);
    }
    
    public void finJeu(String image){
        Victoire v=(Victoire) listPanel.get("Victoire");
        v.afficheImage(image);
    }
    
    public void changePanel(String panel){
        
        Musik.changeMusik(panel);
        Panel p=(Panel) listPanel.get(panel);
        
        fenetre.setScene(p.getScene());
        
    }
    
    
    
}
