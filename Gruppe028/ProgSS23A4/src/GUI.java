package de.ostfalia.prog.ss23;

import de.ostfalia.prog.ss23.enums.Farbe;
import de.ostfalia.prog.ss23.exceptions.FalscheSpielerzahlException;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {
    private Paradiesspiel spiel;
    private Wuerfel zahlenwuerfel;
    private Wuerfel farbenwuerfel;
    private final int[] wurf = new int[2];
    private final Insets inset5 = new Insets(5);

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        startSpiel(primaryStage);
    }

    private void startSpiel(Stage primaryStage) {

        BorderPane selectionScreenRoot = new BorderPane();

        RadioButton[] radioButtons = new RadioButton[6];
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new RadioButton(Farbe.values()[i].toString());
        }

        Button normalSpielButton = new Button("Neues Paradiesspiel");
        Label normalSpielLabel = new Label("2 Figuren, 64 Felder");
        Label normalSpielErrorLabel = new Label("   Zu wenig Figuren!");
        HBox normalSpielHBox = new HBox();
        normalSpielHBox.getChildren().add(normalSpielErrorLabel);
        normalSpielHBox.getChildren().add(normalSpielButton);
        normalSpielHBox.getChildren().add(normalSpielLabel);
        normalSpielHBox.getChildren().get(0).setVisible(false);
        normalSpielHBox.getChildren().get(2).setVisible(false);
        normalSpielHBox.setAlignment(Pos.CENTER);

        Button sommerSpielButton = new Button("Neues Paradiesspiel Sommer");
        Label sommerSpielLabel = new Label("3 Figuren, 72 Felder");
        Label sommerSpielErrorLabel = new Label("   Zu wenig Figuren!");
        HBox sommerSpielHBox = new HBox();
        sommerSpielHBox.getChildren().add(sommerSpielErrorLabel);
        sommerSpielHBox.getChildren().add(sommerSpielButton);
        sommerSpielHBox.getChildren().add(sommerSpielLabel);
        sommerSpielHBox.getChildren().get(0).setVisible(false);
        sommerSpielHBox.getChildren().get(2).setVisible(false);
        sommerSpielHBox.setAlignment(Pos.CENTER);

        Button loadGameButton = new Button("Spiel Laden");

        Label paradiesspielLabel = new Label("Paradiesspiel");
        paradiesspielLabel.setFont(Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 50));

        VBox gameConfiguration = new VBox();
        for (RadioButton radioButton : radioButtons) {
            gameConfiguration.getChildren().add(radioButton);
        }
        gameConfiguration.getChildren().add(normalSpielHBox);
        gameConfiguration.getChildren().add(sommerSpielHBox);
        gameConfiguration.getChildren().add(loadGameButton);
        for (int i = 0; i < gameConfiguration.getChildren().size(); i++) {
            VBox.setMargin(gameConfiguration.getChildren().get(i), inset5);
        }
        gameConfiguration.setAlignment(Pos.CENTER);

        selectionScreenRoot.setCenter(gameConfiguration);
        selectionScreenRoot.setTop(paradiesspielLabel);

        BorderPane.setAlignment(selectionScreenRoot.getTop(), Pos.CENTER);

        Scene newGameScene = new Scene(selectionScreenRoot, 600, 400);
        primaryStage.setTitle("Paradiesspiel");
        primaryStage.setScene(newGameScene);
        primaryStage.show();

        normalSpielButton.setOnMouseEntered(mouseEvent -> normalSpielHBox.getChildren().get(2).setVisible(true));
        normalSpielButton.setOnMouseExited(mouseEvent -> normalSpielHBox.getChildren().get(2).setVisible(false));

        sommerSpielButton.setOnMouseEntered(mouseEvent -> sommerSpielHBox.getChildren().get(2).setVisible(true));
        sommerSpielButton.setOnMouseExited(mouseEvent -> sommerSpielHBox.getChildren().get(2).setVisible(false));

        loadGameButton.setOnAction(actionEvent -> {
            try {
                spiel = (Paradiesspiel) Paradiesspiel.laden("ProgSS23A4/gespeichertesSpiel.txt");
            } catch (IOException | FalscheSpielerzahlException e) {
                throw new RuntimeException(e);
            }
            zahlenwuerfel = new Wuerfel(6);
            farbenwuerfel = new Wuerfel(spiel.getAlleSpieler());
            updateSpiel(primaryStage);
        });

        normalSpielButton.setOnAction(actionEvent -> {
            int anzahlMitspieler = 0;
            for (RadioButton rb : radioButtons) {
                if (rb.isSelected()) {
                    anzahlMitspieler++;
                }
            }
            Farbe[] mitspieler = new Farbe[anzahlMitspieler];
            int i = 0;
            for (int j = 0; j < radioButtons.length; j++) {
                if (radioButtons[j].isSelected()) {
                    mitspieler[i] = Farbe.values()[j];
                    i++;
                }
            }
            if (anzahlMitspieler < 2) {
                normalSpielHBox.getChildren().get(0).setVisible(true);
            } else {
                try {
                    spiel = new Paradiesspiel(mitspieler);
                } catch (FalscheSpielerzahlException e) {
                    throw new RuntimeException(e);
                }
                zahlenwuerfel = new Wuerfel(6);
                farbenwuerfel = new Wuerfel(spiel.getAlleSpieler());
                updateSpiel(primaryStage);
            }
        });

        sommerSpielButton.setOnAction(actionEvent -> {
            int anzahlMitspieler = 0;
            for (RadioButton rb : radioButtons) {
                if (rb.isSelected()) {
                    anzahlMitspieler++;
                }
            }
            Farbe[] mitspieler = new Farbe[anzahlMitspieler];
            int i = 0;
            for (int j = 0; j < radioButtons.length; j++) {
                if (radioButtons[j].isSelected()) {
                    mitspieler[i] = Farbe.values()[j];
                    i++;
                }
            }
            if (anzahlMitspieler < 2) {
                sommerSpielHBox.getChildren().get(0).setVisible(true);
            } else {
                try {
                    spiel = new ParadiesspielSommer(mitspieler);
                } catch (FalscheSpielerzahlException e) {
                    throw new RuntimeException(e);
                }
                zahlenwuerfel = new Wuerfel(6);
                farbenwuerfel = new Wuerfel(spiel.getAlleSpieler());
                updateSpiel(primaryStage);
            }
        });
    }

    private void updateSpiel(Stage primaryStage) {
        wurf[0] = zahlenwuerfel.zahlWuerfeln();
        wurf[1] = zahlenwuerfel.zahlWuerfeln();

        spiel.setFarbeAmZug(farbenwuerfel.farbeWuerfeln());

        BorderPane spielRoot = new BorderPane();
        Label amZugLabel = new Label(spiel.getFarbeAmZug().toString() + " ist am Zug und hat " +
                wurf[0] + ", " + wurf[1] + " gewuerfelt!");
        Label aussetzenLabel = new Label(spiel.getFarbeAmZug().toString() + " muss aussetzen!");

        StackPane[] felderStacks = new StackPane[spiel.getSpielfeld().length];

        for (int i = 0; i < felderStacks.length; i++) {
            felderStacks[i] = new StackPane();
            Button feldButton = new Button((i+1) + " " + spiel.getSpielfeld()[i].toString());
            feldButton.setMinSize(100,150);
            felderStacks[i].getChildren().add(feldButton);
            FlowPane figurenPlane = new FlowPane();
            figurenPlane.setMaxWidth(100);
            for (Figur figur : spiel.getSpielfeld()[i].getFigurenAufFeld()) {
                StackPane sp = new StackPane();
                Circle c = new Circle();
                c.setRadius(10);
                c.setFill(Farbe.valueOf(figur.getName().substring(0, figur.getName().length() - 2)).getColor());
                Text text = new Text(figur.getName().substring(figur.getName().length() - 1));
                text.setX(c.getCenterX());
                text.setY(c.getCenterY());
                text.setTextAlignment(TextAlignment.CENTER);
                sp.getChildren().add(c);
                sp.getChildren().add(text);
                figurenPlane.getChildren().add(sp);
            }
            felderStacks[i].getChildren().add(figurenPlane);
            felderStacks[i].setMinSize(75, 75);
        }

        HBox felderHBox = new HBox();
        for (StackPane felderStack : felderStacks) {
            felderHBox.getChildren().add(felderStack);
        }

        ScrollPane felderScrollPlane = new ScrollPane(felderHBox);

        Button[] figurAuswahlButtons = new Button[spiel.getSpieler(spiel.getFarbeAmZug()).getFiguren().length + 1];
        figurAuswahlButtons[figurAuswahlButtons.length - 1] = new Button("Spiel Speichern");
        for (int i = 0; i < figurAuswahlButtons.length - 1; i++) {
            figurAuswahlButtons[i] = new Button("Figur " + (char) (i+65));
        }

        HBox figurAuswahlHBox = new HBox();
        for (Button button : figurAuswahlButtons) {
            figurAuswahlHBox.getChildren().add(button);
        }
        for (int i = 0; i < figurAuswahlHBox.getChildren().size(); i++) {
            HBox.setMargin(figurAuswahlHBox.getChildren().get(i), inset5);
        }
        figurAuswahlHBox.setAlignment(Pos.CENTER);

        if (spiel.getSpieler(spiel.getFarbeAmZug()).getAussetzen()) {
            spielRoot.setTop(aussetzenLabel);
        } else {
            spielRoot.setTop(amZugLabel);
        }
        spielRoot.setCenter(felderScrollPlane);
        spielRoot.setBottom(figurAuswahlHBox);

        BorderPane.setAlignment(spielRoot.getTop(), Pos.CENTER);

        primaryStage.setTitle(spiel.toString());
        primaryStage.setScene(new Scene(spielRoot, 600, 300));
        primaryStage.show();

        figurAuswahlButtons[figurAuswahlButtons.length - 1].setOnAction(actionEvent -> {
            try {
                spiel.speichern("ProgSS23A4/gespeichertesSpiel.txt");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.close();
        });

        for (int i = 0; i < figurAuswahlButtons.length - 1; i++) {
            int finalI = i;
            figurAuswahlButtons[i].setOnAction(actionEvent -> {
                spiel.bewegeFigur(spiel.getSpieler(spiel.getFarbeAmZug()).getFigur(finalI).getName(), wurf);
                if ((spiel.getGewinner() != null)) {
                    winScreen(primaryStage);
                } else {
                    updateSpiel(primaryStage);
                }
            });
        }
    }

    private void winScreen(Stage primaryStage) {
        VBox winScreenVBox = new VBox();
        Button restartButton = new Button("Neues Spiel");
        Label gewinnerLabel = new Label(spiel.getGewinner().toString() + " gewinnt!");

        winScreenVBox.getChildren().add(gewinnerLabel);
        winScreenVBox.getChildren().add(restartButton);
        winScreenVBox.setPadding(inset5);
        winScreenVBox.setAlignment(Pos.CENTER);

        primaryStage.setScene(new Scene(winScreenVBox, 600, 300));
        primaryStage.show();

        restartButton.setOnAction(actionEvent -> startSpiel(primaryStage));
    }
}
