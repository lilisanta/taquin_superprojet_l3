/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projettaquin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vue.DistributeurPanel;

/**
 * classe principale qui lance l'application en version graphique
 */
public class ProjetTaquin extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setResizable(false);
        stage.setTitle("Projet Taquin");
        
        DistributeurPanel dp=new DistributeurPanel(stage);
        dp.changePanel("menu");
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(args.length==0)
        launch(args);
        else
            new taquinconsole.TaquinConsole().main(args);
    }
    
}
