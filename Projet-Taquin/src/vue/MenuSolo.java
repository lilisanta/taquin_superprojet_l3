/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.io.File;
import javafx.animation.ScaleTransition;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.util.Duration;

/**
 * classe permettant la création ou le chargement des parties
 * @author Mathieu
 */
public class MenuSolo implements Panel {

    private Scene scene;
    private String source;
    private ListView<Label> lv = new ListView<Label>();
    private int tailleCase=3;
    private String imageSource="image-taquin1.jpg";
    private SonGraph son;

    
    /**
     * Constructeur du panel de menu servant à créer ou charger une partie
     * @param dp 
     */
    public MenuSolo(DistributeurPanel dp) {
        Group group = new Group();
        source = "file:///" + System.getProperty("user.dir") + "\\media\\images\\";
        source = source.replace('\\', '/');
        scene = new Scene(group, 800, 600, new ImagePattern(new Image(source + "fond.png")));

        Font fs = Font.font("Papyrus", FontPosture.REGULAR, 25);

        
        // Barre de Navigation
        Label menu = new Label("Menu");
        menu.setFont(fs);
        menu.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                dp.changePanel("menu");
            }

        });

        son = new SonGraph();

        HBox hb = new HBox();
        hb.setSpacing(100);
        hb.setMinWidth(scene.getWidth());
        hb.setMinHeight(80);
        hb.setAlignment(Pos.CENTER);

        Label creer = new Label("Créer Partie");
        creer.setFont(fs);

        Label charger = new Label("Charger Partie");
        charger.setFont(fs);

        hb.getChildren().add(menu);
        hb.getChildren().add(creer);
        hb.getChildren().add(charger);
        hb.getChildren().add(son);
        
        
        // préparation au choix de difficulté
        HBox choixDifficulte=new HBox();
        Background bg=new Background(new BackgroundFill(new ImagePattern(new Image(source + "fondP1.png")),new CornerRadii(20), Insets.EMPTY));
        Border br=new Border(new BorderStroke(Color.rgb(235,235,235), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2)));
        Border brSelected=new Border(new BorderStroke(Color.rgb(35,23,235), BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2)));
        ScaleTransition stE = new ScaleTransition(Duration.millis(500));
        stE.setToX(1.25);
        stE.setToY(1.25);
        ScaleTransition stS = new ScaleTransition(Duration.millis(500));
        stS.setToX(1);
        stS.setToY(1);
        
        // Création et animation du choix de difficulté
        Label case3=new Label("3 x 3");
        case3.setMinWidth(105);
        case3.setMinHeight(105);
        case3.setBackground(bg);
        case3.setAlignment(Pos.CENTER);
        case3.setFont(fs);
        case3.setTextFill(Color.rgb(35,25,235));
        case3.setBorder(brSelected);
        case3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stE.setNode(case3);
                stE.play();
            }
        });
        case3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stS.setNode(case3);
                stS.play();
            }
        });
        
        
        Label case4=new Label("4 x 4");
        case4.setMinWidth(105);
        case4.setMinHeight(105);
        case4.setBackground(bg);
        case4.setAlignment(Pos.CENTER);
        case4.setFont(fs);
        case4.setTextFill(Color.rgb(235,235,235));
        case4.setBorder(br);
        case4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stE.setNode(case4);
                stE.play();
            }
        });
        case4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stS.setNode(case4);
                stS.play();
            }
        });
        
        Label case5=new Label("5 x 5");
        case5.setMinWidth(105);
        case5.setMinHeight(105);
        case5.setBackground(bg);
        case5.setAlignment(Pos.CENTER);
        case5.setFont(fs);
        case5.setTextFill(Color.rgb(235,235,235));
        case5.setBorder(br);
        case5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stE.setNode(case5);
                stE.play();
            }
        });
        case5.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stS.setNode(case5);
                stS.play();
            }
        });
        
        case3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tailleCase=3;
                case3.setTextFill(Color.rgb(35, 25, 235));
                case4.setTextFill(Color.rgb(235, 235, 235));
                case5.setTextFill(Color.rgb(235, 235, 235));
                case3.setBorder(brSelected);
                case4.setBorder(br);
                case5.setBorder(br);
            }
        });
        case4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tailleCase=4;
                case4.setTextFill(Color.rgb(35, 25, 235));
                case3.setTextFill(Color.rgb(235, 235, 235));
                case5.setTextFill(Color.rgb(235, 235, 235));
                case4.setBorder(brSelected);
                case3.setBorder(br);
                case5.setBorder(br);
            }
        });
        case5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tailleCase=5;
                case5.setTextFill(Color.rgb(35, 25, 235));
                case4.setTextFill(Color.rgb(235, 235, 235));
                case3.setTextFill(Color.rgb(235, 235, 235));
                case5.setBorder(brSelected);
                case4.setBorder(br);
                case3.setBorder(br);
            }
        });

        choixDifficulte.getChildren().addAll(case3,case4,case5);
        choixDifficulte.setSpacing(121.25);
        choixDifficulte.setTranslateY(-20);
        choixDifficulte.setTranslateX(10);
        
        
        
        
        
        
        
        HBox choixImageHaut=new HBox();
        choixImageHaut.setSpacing(110);
        HBox choixImageBas=new HBox();
        choixImageBas.setSpacing(120);
        choixImageBas.setTranslateX(115);
        HBox boutons=new HBox();
        boutons.setSpacing(300);
        
        
        // Création des images
        Label image1=new Label();
        image1.setMinWidth(120);
        image1.setMinHeight(120);
        image1.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source + "image-taquin1.jpg")),new CornerRadii(20), Insets.EMPTY)));
        image1.setBorder(brSelected);
        Label image2=new Label();
        image2.setMinWidth(120);
        image2.setMinHeight(120);
        image2.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source + "image-taquin2.jpg")),new CornerRadii(20), Insets.EMPTY)));
        image2.setBorder(br);
        Label image3=new Label();
        image3.setMinWidth(120);
        image3.setMinHeight(120);
        image3.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source + "image-taquin3.jpg")),new CornerRadii(20), Insets.EMPTY)));
        image3.setBorder(br);
        Label image4=new Label();
        image4.setMinWidth(120);
        image4.setMinHeight(120);
        image4.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source + "image-taquin4.jpg")),new CornerRadii(20), Insets.EMPTY)));
        image4.setBorder(br);
        Label image5=new Label();
        image5.setMinWidth(120);
        image5.setMinHeight(120);
        image5.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(source + "image-taquin5.jpg")),new CornerRadii(20), Insets.EMPTY)));
        image5.setBorder(br);
        
        // Animation image 1 + selection
        image1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stE.setNode(image1);
                stE.play();
            }
        });
        image1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stS.setNode(image1);
                stS.play();
            }
        });
        image1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageSource="image-taquin1.jpg";
                image1.setBorder(brSelected);
                image5.setBorder(br);
                image2.setBorder(br);
                image3.setBorder(br);
                image4.setBorder(br);
            }
        });
        
        // Animation image 2 + selection
        image2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stE.setNode(image2);
                stE.play();
            }
        });
        image2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stS.setNode(image2);
                stS.play();
            }
        });
        image2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageSource="image-taquin2.jpg";
                image2.setBorder(brSelected);
                image1.setBorder(br);
                image5.setBorder(br);
                image3.setBorder(br);
                image4.setBorder(br);
            }
        });
        
        // Animation image 3 + selection
        image3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stE.setNode(image3);
                stE.play();
            }
        });
        image3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stS.setNode(image3);
                stS.play();
            }
        });
        image3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageSource="image-taquin3.jpg";
                image3.setBorder(brSelected);
                image1.setBorder(br);
                image2.setBorder(br);
                image5.setBorder(br);
                image4.setBorder(br);
            }
        });
        
        // Animation image 4 + selection
        image4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stE.setNode(image4);
                stE.play();
            }
        });
        image4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stS.setNode(image4);
                stS.play();
            }
        });
        image4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageSource="image-taquin4.jpg";
                image4.setBorder(brSelected);
                image1.setBorder(br);
                image2.setBorder(br);
                image3.setBorder(br);
                image5.setBorder(br);
            }
        });
        
        // Animation image 5 + selection
        image5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stE.setNode(image5);
                stE.play();
            }
        });
        image5.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stS.setNode(image5);
                stS.play();
            }
        });
        image5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                imageSource="image-taquin5.jpg";
                image5.setBorder(brSelected);
                image1.setBorder(br);
                image2.setBorder(br);
                image3.setBorder(br);
                image4.setBorder(br);
            }
        });
        
        
        choixImageHaut.getChildren().addAll(image1,image2,image3);
        choixImageBas.getChildren().addAll(image4,image5);
        
        
        // choix aléatoire de difficulté et d'image
        Label aleatoire = new Label("Aléatoire");
        aleatoire.setFont(fs);
        aleatoire.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int numImage=(int) (Math.random()*6);
                int nbCase=(int) (Math.random()*6);
                if(nbCase<3) nbCase=3;
                if(numImage<1) numImage=1;
                tailleCase=nbCase;
                imageSource="image-taquin"+numImage+".jpg";
            }
        });
        
        // lance la partie
        Label lancer=new Label("Lancer Partie");
        lancer.setFont(fs);
        lancer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PlateauGraphique pg = new PlateauGraphique(tailleCase,imageSource);
                dp.nouveauJeu(pg, "Solo");
                dp.changePanel("jeu");
            }
        });
        
        boutons.getChildren().addAll(aleatoire,lancer);
        
        VBox voption=new VBox();
        voption.getChildren().addAll(choixImageHaut,choixImageBas,boutons);
        voption.setSpacing(20);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        // liste des parties sauvegardées
        lv.setItems(chargerListView());
        lv.setMinWidth(500);
        
        // lance la partie selectionnée
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

        // deux parties alternativement visibles du menu
        GridPane creerOption = new GridPane();
        creerOption.add(choixDifficulte, 0, 0);
        creerOption.add(voption, 0, 1);
        creerOption.setVisible(true);
        creerOption.setMinWidth(800);
        creerOption.setMinHeight(500);
        creerOption.setTranslateX(0);
        creerOption.setTranslateY(80);
        creerOption.setAlignment(Pos.CENTER);

        GridPane chargerOption = new GridPane();
        chargerOption.add(lv, 0, 0);
        chargerOption.add(start, 0, 1);
        chargerOption.setVisible(false);
        chargerOption.setMinWidth(800);
        chargerOption.setMinHeight(500);
        chargerOption.setTranslateX(0);
        chargerOption.setTranslateY(80);
        chargerOption.setAlignment(Pos.CENTER);

        group.getChildren().add(creerOption);
        group.getChildren().add(chargerOption);
        group.getChildren().add(hb);

        creer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                chargerOption.setVisible(false);
                creerOption.setVisible(true);
            }

        });

        charger.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                creerOption.setVisible(false);
                chargerOption.setVisible(true);
            }

        });
        
        
        // permet l'affichage spécique de la liste des sauvegardes
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

    
    /**
     * méthode pour charger la liste des sauvegardes
     * @return la liste à afficher
     */
    private ObservableList<Label> chargerListView() {
        ObservableList<Label> oal = FXCollections.observableArrayList();
        File repertoire = new File(System.getProperty("user.dir") + "\\media\\plateau");
        if (repertoire.listFiles() == null) {
            oal.add(new Label("Aucune partie enregistrée"));
        } else {
            for (File f : repertoire.listFiles()) {
                oal.add(new Label(f.getName()));
            }
        }

        
        return oal;
    }

    
    /**
     * méthode hérité de Panel
     * effectue un chargement de la liste à chaque appel
     * @return 
     */
    @Override
    public Scene getScene() {
        son.afficheSon();
        lv.setItems(chargerListView());
        return scene;
    }

}
