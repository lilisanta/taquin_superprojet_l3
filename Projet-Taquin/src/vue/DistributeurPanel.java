/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.HashMap;
import javafx.stage.Stage;

/**
 * classe permettant la simplification de l'affichage des panel
 * @author Mathieu
 */
public class DistributeurPanel {
    
    private Stage fenetre;
    private HashMap listPanel;
    
    /**
     * Constructeur permet d'initialiser les differents panel du jeu
     * @param frame Stage qui permet la gestion des panel
     */
    public DistributeurPanel(Stage frame){
        
        fenetre=frame;
        listPanel=new HashMap<String,Panel>();
        
        Menu menu=new Menu(this);
        MenuSolo solo=new MenuSolo(this);
        GuiJeu gui=new GuiJeu(this, new PlateauGraphique(3,"image-taquin1.jpg"),"Solo");
        Victoire vic=new Victoire(this,"image-taquin1.jpg");
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
    
    /**
     * Créer une nouvelle instance de GuiJeu
     * Utile pour la création  ou le chargement de partie
     * @param pg le plateau à résoudre
     * @param type le type de partie initialement prévu (Solo, coopération ou compétition)
     */
    public void nouveauJeu(PlateauGraphique pg, String type){
        GuiJeu gui=new GuiJeu(this,pg,type);
        listPanel.put("jeu",gui);
    }
    
    /**
     * Permet l'affichage de l'image joué complete dans le panel Victoire
     * @param image nom de l'image à afficher
     */
    public void finJeu(String image){
        Victoire v=new Victoire(this, image);
        listPanel.put("Victoire",v);
    }
    
    /**
     * Permet la permutation de panel et de musique
     * @param panel nom du panel à afficher
     */
    public void changePanel(String panel){
        
        Musik.changeMusik(panel);
        Panel p=(Panel) listPanel.get(panel);
        
        fenetre.setScene(p.getScene());
        
    }
    
    
    
}
