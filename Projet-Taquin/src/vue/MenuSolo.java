/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

/**
 *
 * @author Mathieu
 */
public class MenuSolo implements Panel {

    private Scene scene;
    private String source;
    private ListView<Label> lv = new ListView<Label>();

    public MenuSolo(DistributeurPanel dp) {
        Group group = new Group();
        source = "file:///" + System.getProperty("user.dir") + "\\media\\images\\";//sable.jpg";
        source = source.replace('\\', '/');
        scene = new Scene(group, 800, 600, new ImagePattern(new Image(source + "fond.png")));

        Font fs = Font.font("Papyrus", FontPosture.REGULAR, 25);

        Label menu = new Label("Menu");
        menu.setFont(fs);
        //menu.setTextFill(Color.rgb(27, 27, 235));
        menu.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                dp.changePanel("menu");
                System.out.println("Clicketi clicketa");
            }

        });

        SonGraph son = new SonGraph();

        HBox hb = new HBox();
        hb.setSpacing(100);
        hb.setMinWidth(scene.getWidth());
        hb.setMinHeight(140);
        hb.setAlignment(Pos.CENTER);

        Label creer = new Label("Créer Partie");
        creer.setFont(fs);

        Label charger = new Label("Charger Partie");
        charger.setFont(fs);

        hb.getChildren().add(menu);
        hb.getChildren().add(creer);
        hb.getChildren().add(charger);
        hb.getChildren().add(son);

        Label test0 = new Label("Je suis présent");

        lv.setItems(chargerListView());

        //lv.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source+"fondP1.png")),CornerRadii.EMPTY,Insets.EMPTY)));
        lv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(lv.getSelectionModel().getSelectedItem());
            }

        });
        lv.setMinWidth(500);

        Label start = new Label("Lancer Partie");
        start.setFont(fs);
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PlateauGraphique pg = new PlateauGraphique(lv.getSelectionModel().getSelectedItem().getText());
                dp.nouveauJeu(pg, "Solo");
                dp.changePanel("jeu");
            }
        });
        start.setTranslateX(190);

        GridPane creerOption = new GridPane();
        creerOption.add(test0, 0, 0);
        creerOption.setVisible(true);
        creerOption.setMinWidth(800);
        creerOption.setMinHeight(440);
        creerOption.setTranslateX(0);
        creerOption.setTranslateY(160);
        creerOption.setAlignment(Pos.CENTER);

        GridPane chargerOption = new GridPane();
        chargerOption.add(lv, 0, 0);
        chargerOption.add(start, 0, 1);
        chargerOption.setVisible(false);
        chargerOption.setMinWidth(800);
        chargerOption.setMinHeight(440);
        chargerOption.setTranslateX(0);
        chargerOption.setTranslateY(160);
        chargerOption.setAlignment(Pos.CENTER);

        group.getChildren().add(creerOption);
        group.getChildren().add(chargerOption);
        group.getChildren().add(hb);

        creer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                chargerOption.setVisible(false);
                creerOption.setVisible(true);
                System.out.println("Options pour creer une partie");
            }

        });

        charger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                creerOption.setVisible(false);
                chargerOption.setVisible(true);
            }

        });
        
        

        lv.setCellFactory(livi -> new ListCell<Label>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Label item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source + "fondP1.png")), CornerRadii.EMPTY, Insets.EMPTY)));
                } else {
                    label.setText(item.getText());
                    label.setFont(new Font("papyrus",15));
                    label.setTextFill(Color.rgb(235,235,235));
                    setGraphic(label);
                    setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source + "fondP1.png")), CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
        });
        
        lv.setBackground(new Background(new BackgroundFill(Color.rgb(255,255,255), CornerRadii.EMPTY, Insets.EMPTY)));
        
        
        
        
        
    }

    private ObservableList<Label> chargerListView() {
        ObservableList<Label> oal = FXCollections.observableArrayList();
        File repertoire = new File(System.getProperty("user.dir") + "\\media\\plateau");
        if (repertoire.listFiles().length == 0) {
            oal.add(new Label("Aucune partie enregistrée"));
        } else {
            for (File f : repertoire.listFiles()) {
                oal.add(new Label(f.getName()));
            }
        }

        
        return oal;
    }

    @Override
    public Scene getScene() {

        lv.setItems(chargerListView());
        return scene;
    }

}
