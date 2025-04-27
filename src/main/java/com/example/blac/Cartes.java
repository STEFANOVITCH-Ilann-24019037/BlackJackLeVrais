package com.example.blac;



public class Cartes {

    private int NBCarte;
    private int Forme;//1 trefle 2 pic 3 coere 4 carreau
    private boolean EstVisible;
    private int Valeur;

    public Cartes(int NBCarte, int Forme, boolean EstVisible,int valeur) {
        this.NBCarte = NBCarte;
        this.EstVisible = EstVisible;
        this.Forme = Forme;
        this.Valeur = valeur;
    }


    public int getNBCarte() {
        return NBCarte;
    }
    public boolean isEstVisible() {
        return EstVisible;
    }
    public int getForme() {
        return Forme;
    }
    public void setEstVisible(boolean EstVisible) {
        this.EstVisible = EstVisible;
    }
    public int getValeur() {
        return Valeur;
    }
    public void setValeur(int valeur) {
        this.Valeur = valeur;
    }

    public boolean equals(Cartes carte){
        try {
            if (this.NBCarte==carte.getNBCarte() && this.Forme==carte.getForme()&&this.EstVisible==carte.isEstVisible()){
                return true;
            }
        }
        catch (NullPointerException e){
            return false;
        }

        return false;
    }
}
