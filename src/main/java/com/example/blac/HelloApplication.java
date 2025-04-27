package com.example.blac;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;

public class HelloApplication extends Application {

    private static final Cartes[] cartes = new Cartes[ 52];
    private static int jouer = 0;
    private static int Piocher = 0;
    private static int scoreJoueur = 0;
    private static int scoreCroupier = 0;

    @Override
    public void start(Stage stage) throws IOException {

        GridPane root = new GridPane();
        root.setVgap(10);
        Label piocheLabel = new Label();
        piocheLabel.setGraphic(new ImageView("C:\\Users\\ilann\\IdeaProjects\\Blac\\src\\main\\resources\\Cartes\\dos.png"));
        HBox piocheHbox = new HBox(piocheLabel);
        Button button = new Button("poiche");
        Button button2 = new Button("fin");
        HBox Jeu  = new HBox();
        HBox JeuCroupier = new HBox();
        Label scoreLabel = new Label();
        scoreLabel.setText("Score: " + scoreJoueur);
        Label scoreLabelCroupier = new Label();
        scoreLabelCroupier.setText("Score: " + scoreCroupier);
        Label labelVide = new Label();
        labelVide.setMinSize(100,100);
        Jeu.getChildren().add(labelVide);
        int i = 0;
        while (i <2){
            pioche(JeuCroupier,false);
            updateScoreLabel(scoreLabelCroupier,scoreCroupier);
            i++;
        }

        button.setOnAction(e -> {
            Piocher++;
            if (Piocher<=3){
                pioche(Jeu,true);
                updateScoreLabel(scoreLabel,scoreJoueur);
            }else {
                finJeu(Jeu,scoreJoueur,scoreCroupier);
                Piocher=0;
            }
        });

        button2.setOnAction(e -> {
            finJeu(Jeu,scoreJoueur,scoreCroupier);
            Piocher=0;
        });

        root.add(Jeu, 1, 1);
        root.add(piocheHbox, 0, 0);
        root.add(JeuCroupier, 1, 0);
        root.add(scoreLabelCroupier, 5, 0);
        root.add(button, 0, 2);
        root.add(button2, 1, 2);
        root.add(scoreLabel, 0, 1);

        Scene scene = new Scene(root,400,400);
        stage.setTitle("Black Jak");
        stage.setScene(scene);
        stage.show();
    }

    public static int getRandom(int min, int max) {
        int range = (max - min) + 1;
        int random = (int) ((range * Math.random()) + min);
        return random;
    }

    public static void pioche(HBox Jeu,boolean joueur){

        int numCarte = getRandom(1,12);
        int couleur = getRandom(0,3);
        Cartes simuCarte = new Cartes(numCarte,couleur,true,0);
        for(int i=0;i<jouer;i++ ){
            if (simuCarte.equals(cartes[i])){
                numCarte = getRandom(0,12);
                couleur = getRandom(0,3);
                simuCarte = new Cartes(numCarte,couleur,true,0);
                System.out.println("redemande" + numCarte + " " + couleur+i);
                i=0;
            }
        }
        jouer++;
        System.out.println(numCarte);//as: 1 ,...,roi:10 reine:11 valais:12
        System.out.println(couleur);//0 trefle 1 pic 2 coeur 3 carreau
        StringBuffer str = new StringBuffer();
        str.append("C:\\Users\\ilann\\IdeaProjects\\Blac\\src\\main\\resources\\Cartes\\");
        if (numCarte<10 && numCarte>1){
            str.append(numCarte+1);
            simuCarte.setValeur(numCarte+1);
        }
        else if(numCarte==10){
            str.append("roi");
            simuCarte.setValeur(10);
        }
        else if(numCarte==11){
            str.append("reine");
            simuCarte.setValeur(10);
        }
        else if(numCarte==12){
            str.append("valais");
            simuCarte.setValeur(10);
        }
        else if(numCarte==1){
            str.append("as");
            simuCarte.setValeur(1);
        }

        if(couleur==0){
            str.append("_trefle.png");
        }
        else if(couleur==1){
            str.append("_pic.png");
        }
        else if(couleur==2){
            str.append("_coeur.png");
        }
        else if(couleur==3){
            str.append("_carreau.png");
        }

        if (joueur){
            scoreJoueur = scoreJoueur + simuCarte.getValeur();
        }
        else {
            scoreCroupier = scoreCroupier + simuCarte.getValeur();
        }
        Label carte = new Label();
        carte.setGraphic(new ImageView(str.toString()));
        Jeu.getChildren().add(carte);
    }

    public void updateScoreLabel(Label label, int I) {
        label.setText("Score: " + I);
    }

    public void finJeu(HBox Jeu,int scoreJoueur,int scoreCroupier){
        if (scoreJoueur < 21){
            if (scoreJoueur>scoreCroupier){
                System.out.println("score: " + scoreJoueur + " vous avez ganier");
            }
            else{
                System.out.println("score: " + scoreCroupier + " vous avez perdu");
            }
        }
        else if (scoreJoueur == 21&&scoreCroupier!=21) {
            System.out.println("score: " + scoreJoueur + "vous avez gagner");
        }
        else if (scoreJoueur != 21&&scoreCroupier==21) {
            System.out.println("score: " + scoreCroupier + "vous avez perdu");
        }
        else if (scoreJoueur > 21 ){
            System.out.println("Perdu");
        }
        else if (scoreCroupier > 21){
            System.out.println("Vous avez gagner");
        }
        else if (scoreCroupier>21 && scoreJoueur>21) {
            System.out.println("Match nul");
        } else{
            System.out.println("Match nul");
        }
        Jeu.getChildren().clear();
    }

    public static void main(String[] args) {
        launch();
    }
}