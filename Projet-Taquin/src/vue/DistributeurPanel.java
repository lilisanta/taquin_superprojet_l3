/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import testgui.*;
import java.util.HashMap;
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
        
        GuiJeu gui=new GuiJeu(this, new PlateauGraphique());
        
        listPanel.put("jeu", gui);
        
    }
    
    
    
    public void changePanel(String panel){
        Panel p=(Panel) listPanel.get(panel);
        fenetre.setScene(p.getScene());
    }
    
    
    
}
