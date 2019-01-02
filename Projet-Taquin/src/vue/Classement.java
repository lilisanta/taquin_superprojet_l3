/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
import utils.DBHelper;

/**
 *
 * @author Mathieu
 */
public class Classement implements Panel {

    private Scene scene;
    private TableView<Joueur> tv = new TableView<Joueur>();
    private String source;

    public Classement() {
        Group groupe = new Group();
        scene = new Scene(groupe, 800, 600, Color.rgb(225, 225, 255));

    }

    Classement(DistributeurPanel dp) {
        Group groupe = new Group();
        source = "file:///" + System.getProperty("user.dir") + "\\media\\images\\";//sable.jpg";
        source = source.replace('\\', '/');

        scene = new Scene(groupe, 800, 600, new ImagePattern(new Image(source + "fond.png")));

        Font fs = Font.font("Papyrus", FontPosture.REGULAR, 18);

        
        
        
        VBox vb = new VBox();
        vb.setSpacing(25);

        Label menu = new Label("Menu");
        menu.setFont(new Font("papyrus", 30));
        menu.setTranslateX(40);
        menu.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                dp.changePanel("menu");
                System.out.println("Clicketi clicketa");
            }

        });

        SonGraph son = new SonGraph();
        son.setTranslateX(750);
        son.setTranslateY(20);

        
        
        
        
        tv.setMinWidth(760);
        tv.setTranslateX(20);

        TableColumn<Joueur, String> pseudoColonne = new TableColumn<Joueur, String>("Pseudo");
        pseudoColonne.setCellValueFactory(param -> {
            final Joueur j = param.getValue();
            return new SimpleStringProperty(j.pseudo);
        });
        pseudoColonne.setCellFactory(tc -> colonneStyle());

        TableColumn<Joueur, String> coupsColonne = new TableColumn<Joueur, String>("Coups");
        coupsColonne.setCellValueFactory(param -> {
            final Joueur j = param.getValue();
            return new SimpleStringProperty(j.nbcoup);
        });
        coupsColonne.setCellFactory(tc -> colonneStyle());

        TableColumn<Joueur, String> tempsColonne = new TableColumn<Joueur, String>("Temps");
        tempsColonne.setCellValueFactory(param -> {
            final Joueur j = param.getValue();
            return new SimpleStringProperty(j.temps);
        });
        tempsColonne.setCellFactory(tc -> colonneStyle());

        tv.getColumns().addAll(pseudoColonne, coupsColonne, tempsColonne);
        tv.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tv.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));

        
        
        
        
        
        
        
        
        vb.getChildren().add(menu);
        vb.getChildren().add(tv);
        groupe.getChildren().add(vb);
        groupe.getChildren().add(son);
    }

    private void chargerListView() {
        ObservableList<Joueur> oal = FXCollections.observableArrayList();
        List<String> dataDB = DBHelper.getScoresSolo();
        if (dataDB.size() == 0) {

        } else {
            for (int i = 0; i < dataDB.size(); i = i + 3) {
                Joueur j = new Joueur(dataDB.get(i), dataDB.get(i + 1), dataDB.get(i + 2));
                System.out.println(j.toString());
                oal.add(j);
            }
            tv.setItems(oal);
        }

    }

    @Override
    public Scene getScene() {
        chargerListView();
        return scene;
    }
    
    
    
    private TableCell<Joueur, String> colonneStyle(){
        TableCell<Joueur, String> cell = new TableCell<Joueur, String>() {

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                         setFont(new Font("papyrus", 25));
                        setTextFill(Color.rgb(235, 235, 235));
                        setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source + "fondP1.png")), CornerRadii.EMPTY, Insets.EMPTY)));

                    } else {
                        setText(item);
                        setFont(new Font("papyrus", 25));
                        setTextFill(Color.rgb(235, 235, 235));
                        setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source + "fondP1.png")), CornerRadii.EMPTY, Insets.EMPTY)));

                    }
                }
            };
            return cell;
    }
    
    
    

    private class Joueur {

        String pseudo, nbcoup, temps;

        Joueur(String ps, String coups, String time) {
            pseudo = ps;
            nbcoup = coups;
            temps = time;
        }

        public String toString() {
            return "Moi je suis " + pseudo + " et j'ai réussi en " + nbcoup + " sur une durée de " + temps;
        }

    }

}
